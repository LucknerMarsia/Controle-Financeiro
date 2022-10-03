package com.br.marsia.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.marsia.controlefinanceiro.exception.DespesaInvalidaException;
import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;
import com.br.marsia.controlefinanceiro.repository.DespesasEventuaisRepository;
import com.br.marsia.controlefinanceiro.util.ConverteDataPeriodoMes;
import com.br.marsia.controlefinanceiro.util.PeriodoMes;



@Service
public class DespesasEventuaisService {
	
	@Autowired
	private DespesasEventuaisRepository repository;
	
	public List<DespesasEventuais> listar(String descricao){
		if (descricao == null) {
			return this.repository.findAll();
		} else {
			return this.repository.findByDescricao(descricao);
		}
	}

	public Optional<DespesasEventuais> consultar(Long id) {
		return this.repository.findById(id);
	}

	public void salvar(DespesasEventuais despesa) throws DespesaInvalidaException {
		
		PeriodoMes periodoMes = new ConverteDataPeriodoMes().converter(despesa.getData());
		List<DespesasEventuais> listaDespesa;
		
		System.out.println("ID NO SAVE : " + despesa.getId());
		

		if (despesa.getId() != null) {
			listaDespesa = this.repository.buscaDespesaPorDescricaoEPeriodoDeDataEId(despesa.getId(), despesa.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		} else {
			listaDespesa = this.repository.buscaDespesaPorDescricaoEPeriodoDeData(despesa.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		}
		
		if (listaDespesa == null || listaDespesa.isEmpty()) {
			this.repository.save(despesa);
		} else {
			throw new DespesaInvalidaException("Já existe Despesa com este nome para o mesmo mês/ano");
		}
		
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	public List<DespesasEventuais> listarPorAnoMes(Integer ano, Long mes) {
		LocalDate dataBase = LocalDate.of(ano, mes.intValue(), 01);
		
		PeriodoMes periodoMes = new ConverteDataPeriodoMes().converter(dataBase);
		
		return this.repository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
	}
}


