package com.br.marsia.controlefinanceiro.util;

import java.time.LocalDate;


public class PeriodoMes {

	private LocalDate dtini;
	private LocalDate dtfim;

	public PeriodoMes(LocalDate dtini, LocalDate dtfim) {
		this.dtini = dtini;
		this.dtfim = dtfim;
	}

	public LocalDate getDtini() {
		return dtini;
	}

	public LocalDate getDtfim() {
		return dtfim;
	}
	
}
