package com.br.marsia.controlefinanceiro.util;

import java.time.LocalDate;

public class ConverteDataPeriodoMes {
	
	public PeriodoMes converter(LocalDate data) {
	
		LocalDate dtini = LocalDate.of(data.getYear(), data.getMonth(), 1);
		
		
		PeriodoMes periodoMes = new PeriodoMes(dtini ,dtini.plusMonths(1).minusDays(1));

		return periodoMes;
	}

}
