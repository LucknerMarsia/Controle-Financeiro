package com.br.marsia.controlefinanceiro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.marsia.controlefinanceiro.controlefinal.GeradorControleFinal;
import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;
import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;
import com.br.marsia.controlefinanceiro.modelo.OrcamentoFinal;
import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;
import com.br.marsia.controlefinanceiro.repository.DespesasEventuaisRepository;
import com.br.marsia.controlefinanceiro.repository.DespesasFixasRepository;
import com.br.marsia.controlefinanceiro.repository.ReceitaMensalRepository;
import com.br.marsia.controlefinanceiro.util.ConverteDataPeriodoMes;
import com.br.marsia.controlefinanceiro.util.PeriodoMes;



@Service
public class OrcamentoFinalService {
	
	@Autowired
	private DespesasFixasRepository despesaFixasRepository;
	
	@Autowired
	private DespesasEventuaisRepository despesaEventuaisRepository;
	
	@Autowired
	private ReceitaMensalRepository receitaRepository;
	
	
	public OrcamentoFinal listar(int ano, int mes) {

		LocalDate dataBase = LocalDate.of(ano, mes, 01);
		
		PeriodoMes periodoMes = new ConverteDataPeriodoMes().converter(dataBase);
		
		List<ReceitaMensal> listaReceitaMensal = this.receitaRepository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
		
		List<DespesasFixas> listaDespesasFixas = this.despesaFixasRepository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());

		List<DespesasEventuais> listaDespesasEventuais = this.despesaEventuaisRepository.buscaPorPeriodo(periodoMes.getDtini(), periodoMes.getDtfim());
		
		GeradorControleFinal geradorControle = new GeradorControleFinal(listaReceitaMensal, listaDespesasFixas, listaDespesasEventuais);
		
		return geradorControle.gerar();
		
	}
	

}

