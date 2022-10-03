package com.br.marsia.controlefinanceiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;



public class ReceitaMensalDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	public ReceitaMensalDto(ReceitaMensal receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
	}
	
	public Long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public LocalDate getData() {
		return data;
	}

	public static List<ReceitaMensalDto> converter(List<ReceitaMensal> listaReceitas) {
		List<ReceitaMensalDto> listaReceitaDto = listaReceitas.stream().map(rec -> new ReceitaMensalDto(rec)).collect(Collectors.toList());
		
		return listaReceitaDto;
	}
	
	
	

}

