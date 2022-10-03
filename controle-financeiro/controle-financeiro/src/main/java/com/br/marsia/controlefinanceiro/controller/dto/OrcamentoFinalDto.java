package com.br.marsia.controlefinanceiro.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import com.br.marsia.controlefinanceiro.modelo.OrcamentoFinal;
import com.br.marsia.controlefinanceiro.modelo.ValorTotalCategoria;



public class OrcamentoFinalDto {
	
	private BigDecimal valorTotalReceitas;
	private BigDecimal valorTotalDespesasFixas;
	private BigDecimal valorTotalDespesasEventuais;
	private BigDecimal valorFinalMes;


	private List<ValorTotalCategoria> ListaValorTotalCategoria;
	
	public void adicionaCategoria(ValorTotalCategoria valorTotalCategoria) {
		this.ListaValorTotalCategoria.add(valorTotalCategoria);
	}

	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public void setValorTotalReceitas(BigDecimal valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}

	public BigDecimal getValorTotalDespesasFixas() {
		return valorTotalDespesasFixas;
	}

	public void setValorTotalDespesasFixas(BigDecimal valorTotalDespesasFixas) {
		this.valorTotalDespesasFixas = valorTotalDespesasFixas;
	}
	public BigDecimal getValorTotalDespesasEventuais() {
		return valorTotalDespesasEventuais;
	}

	public void setValorTotalDespesasEventuais(BigDecimal valorTotalDespesasEventuais) {
		this.valorTotalDespesasEventuais = valorTotalDespesasEventuais;
	}

	public BigDecimal getValorFinalMes() {
		return valorFinalMes;
	}

	public void setValorFinalMes(BigDecimal valorFinalMes) {
		this.valorFinalMes = valorFinalMes;
	}

	public List<ValorTotalCategoria> getListaValorTotalCategoria() {
		return ListaValorTotalCategoria;
	}
	
	
	public void setListaValorTotalCategoria(List<ValorTotalCategoria> listaValorTotalCategoria) {
		ListaValorTotalCategoria = listaValorTotalCategoria;
	}

	public OrcamentoFinalDto converter(OrcamentoFinal resumo) {
		
		OrcamentoFinalDto resumoDto = new OrcamentoFinalDto();
		resumoDto.setValorTotalReceitas(resumo.getValorTotalReceitas());
		resumoDto.setValorTotalDespesasFixas(resumo.getValorTotalDespesasFixas());
		resumoDto.setValorTotalDespesasEventuais(resumo.getValorTotalDespesasEventuais());
		resumoDto.setValorFinalMes(resumo.getValorFinalMes());
		resumoDto.setListaValorTotalCategoria(resumo.getListaValorTotalCategoria());
		
		return resumoDto;
	}
	
}

