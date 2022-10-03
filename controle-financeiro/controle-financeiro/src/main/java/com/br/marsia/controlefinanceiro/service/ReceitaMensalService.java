package com.br.marsia.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.marsia.controlefinanceiro.exception.ReceitaInvalidaException;
import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;
import com.br.marsia.controlefinanceiro.repository.ReceitaMensalRepository;
import com.br.marsia.controlefinanceiro.util.ConverteDataPeriodoMes;
import com.br.marsia.controlefinanceiro.util.PeriodoMes;


@Service
public class ReceitaMensalService {
	
	@Autowired
	private ReceitaMensalRepository repository;
	
	public List<ReceitaMensal> listar(String descricao){
		if (descricao == null) {
			return this.repository.findAll();
		} else {
			return this.repository.findByDescricao(descricao);
		}
	}
	
	public void salvar(ReceitaMensal receita) throws ReceitaInvalidaException {

		PeriodoMes periodoMes = new ConverteDataPeriodoMes().converter(receita.getData());
		
		List<ReceitaMensal> listaReceita;
		if (receita.getId() != null) {
			listaReceita = this.repository.buscaReceitaPorDescricaoEPeriodoDeDataEId(receita.getId(), receita.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		} else {
			listaReceita = this.repository.buscaReceitaPorDescricaoEPeriodoDeData(receita.getDescricao(), periodoMes.getDtini(), periodoMes.getDtfim());
		}
		
		if (listaReceita == null || listaReceita.isEmpty()) {
			this.repository.save(receita);
		} else {
			throw new ReceitaInvalidaException("Já existe Receita com este nome para o mesmo mês/ano");
		}
	}

	public Optional<ReceitaMensal> consultar(Long id) {
		return this.repository.findById(id);
	}

	public void deletar(Long id) {
		this.repository.deleteById(id);
	}

	public List<ReceitaMensal> listarPorAnoMes(Integer ano, Integer mes) {
		LocalDate dataBase = LocalDate.of(ano, mes, 01);
		
		PeriodoMes periodoMes = new ConverteDataPeriodoMes().converter(dataBase);
		
		return this.repository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
	}


}

