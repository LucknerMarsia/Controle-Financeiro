package com.br.marsia.controlefinanceiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.marsia.controlefinanceiro.modelo.Categoria;
import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;



public class DespesasFixasDto {
	
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;
	
	public DespesasFixasDto(DespesasFixas despesa) {
		this.id = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public static List<DespesasFixasDto> converter(List<DespesasFixas> listaDespesas) {
		List<DespesasFixasDto> listaDespesaDto = listaDespesas.stream()
				.map(dep -> new DespesasFixasDto(dep)).collect(Collectors.toList());
		
		return listaDespesaDto;
	}
}
