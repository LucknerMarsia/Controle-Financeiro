package com.br.marsia.controlefinanceiro.modelo;

import java.math.BigDecimal;

public class ValorTotalCategoria {
	
	
	private Categoria categoria;
	private BigDecimal valorTotal;
	
	public ValorTotalCategoria(Categoria categoria, BigDecimal valorTotal) {
		this.categoria = categoria;
		this.valorTotal = valorTotal;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
}