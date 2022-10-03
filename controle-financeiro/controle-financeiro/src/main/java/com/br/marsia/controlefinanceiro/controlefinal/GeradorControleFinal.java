package com.br.marsia.controlefinanceiro.controlefinal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.br.marsia.controlefinanceiro.modelo.Categoria;
import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;
import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;
import com.br.marsia.controlefinanceiro.modelo.OrcamentoFinal;
import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;
import com.br.marsia.controlefinanceiro.modelo.ValorTotalCategoria;
import com.br.marsia.controlefinanceiro.util.CategoriaDespesasEventuaisComparador;
import com.br.marsia.controlefinanceiro.util.CategoriaDespesasFixasComparador;



public class GeradorControleFinal {
	
	private List<ReceitaMensal> listaReceitas;
	private List<DespesasFixas> listaDespesasFixas;
	private List<DespesasEventuais> listaDespesasEventuais;
	
	
	public GeradorControleFinal(List<ReceitaMensal> listaReceitas, List<DespesasFixas> listaDespesasFixas, List<DespesasEventuais> listaDespesasEventuais) {
		this.listaReceitas = listaReceitas;
		this.listaDespesasFixas = listaDespesasFixas;
		this.listaDespesasEventuais = listaDespesasEventuais;
	}
	
	

	public OrcamentoFinal gerar() {
		
		BigDecimal valorTotalReceita = this.listaReceitas
				.stream()
				.map(ReceitaMensal::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		
		BigDecimal valorTotalDespesasFixas = this.listaDespesasFixas
				.stream()
				.map(DespesasFixas::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		
		 BigDecimal valorTotalDespesasEventuais = this.listaDespesasEventuais
				.stream()
				.map(DespesasEventuais::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		 
		BigDecimal valorTotaldespesas = valorTotalDespesasFixas.add(valorTotalDespesasEventuais);

		BigDecimal valorSaldoFinalMes = valorTotalReceita.subtract(valorTotaldespesas);
		
		List<ValorTotalCategoria> listaValorPorCategoria = gerarDespesaPorCategoria();
		
		
		return new OrcamentoFinal(valorTotalReceita, valorTotalDespesasFixas, valorTotalDespesasEventuais,  valorSaldoFinalMes ,listaValorPorCategoria);
	}
	 
		
	
	   
		private List<ValorTotalCategoria> gerarDespesaPorCategoria() {
	    	   	    
		if (this.listaDespesasFixas == null || this.listaDespesasFixas.isEmpty()) {
			return null;
		}
		
		if (this.listaDespesasEventuais == null || this.listaDespesasEventuais.isEmpty()) {
			return null;
		}
		
		
		this.listaDespesasFixas.sort(new CategoriaDespesasFixasComparador());
		this.listaDespesasEventuais.sort(new CategoriaDespesasEventuaisComparador());
		
	
		Categoria  ultimaCategoria = null;
		BigDecimal valor = BigDecimal.ZERO;
				
		List<ValorTotalCategoria> listaValorDespesasFixasTotalCategoria = new ArrayList<>();

		
		for (DespesasFixas despesaFixa : this.listaDespesasFixas) {
			
			if (ultimaCategoria == null) {
				ultimaCategoria = despesaFixa.getCategoria();
				valor = despesaFixa.getValor();
				
			} else if (ultimaCategoria.getCodigo().intValue() == despesaFixa.getCategoria().getCodigo().intValue()) {
				valor = valor.add(despesaFixa.getValor());
				
			} else {
				listaValorDespesasFixasTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
				
				ultimaCategoria = despesaFixa.getCategoria();
				valor = despesaFixa.getValor();
			}
		}
		
		listaValorDespesasFixasTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
		
List<ValorTotalCategoria> listaValorDespesasEventuaisTotalCategoria = new ArrayList<>();
		
	
		
		for (DespesasEventuais despesaEventual : this.listaDespesasEventuais) {
			
			if (ultimaCategoria == null) {
				ultimaCategoria = despesaEventual.getCategoria();
				valor = despesaEventual.getValor();
				
			} else if (ultimaCategoria.getCodigo().intValue() == despesaEventual.getCategoria().getCodigo().intValue()) {
				valor = valor.add(despesaEventual.getValor());
				
			} else {
				listaValorDespesasEventuaisTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
				
				ultimaCategoria = despesaEventual.getCategoria();
				valor = despesaEventual.getValor();
			}
		}
		
		
		listaValorDespesasEventuaisTotalCategoria.add(new ValorTotalCategoria(ultimaCategoria, valor));
		
		
		ArrayList<ValorTotalCategoria> totalLista = new ArrayList<ValorTotalCategoria>(listaValorDespesasFixasTotalCategoria.size() + listaValorDespesasEventuaisTotalCategoria.size());
		totalLista.addAll(listaValorDespesasEventuaisTotalCategoria);
		totalLista.addAll(listaValorDespesasFixasTotalCategoria);
		
		
		return totalLista;
	}
}
