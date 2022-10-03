package com.br.marsia.controlefinanceiro.modelo;

import java.math.BigDecimal;
import java.util.List;




public class OrcamentoFinal {
	
	private BigDecimal valorTotalReceitas;
	private BigDecimal valorTotalDespesasFixas;
	private BigDecimal valorTotalDespesasEventuais;
	private BigDecimal valorFinalMes;
	
	private List<ValorTotalCategoria> ListaValorTotalCategoria;
	
	
	public OrcamentoFinal(BigDecimal valorTotalReceitas, BigDecimal valorTotalDespesasFixas, BigDecimal valorTotalDespesasEventuais,  BigDecimal valorFinalMes,
			List<ValorTotalCategoria> listaValorTotalCategoria) {
		this.valorTotalReceitas = valorTotalReceitas;
		this.valorTotalDespesasFixas = valorTotalDespesasFixas;
		this.valorTotalDespesasEventuais = valorTotalDespesasEventuais;
		this.valorFinalMes = valorFinalMes;
		this.ListaValorTotalCategoria = listaValorTotalCategoria;
	}

	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public BigDecimal getValorTotalDespesasFixas() {
		return valorTotalDespesasFixas;
	}
	public BigDecimal getValorTotalDespesasEventuais() {
		return valorTotalDespesasEventuais;
	}

	public BigDecimal getValorFinalMes() {
		return valorFinalMes;
	}

	public List<ValorTotalCategoria> getListaValorTotalCategoria() {
		return this.ListaValorTotalCategoria;
	}

}

