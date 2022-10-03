package com.br.marsia.controlefinanceiro.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.marsia.controlefinanceiro.modelo.Categoria;
import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;




public class DespesasEventuaisDto {
	
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;
	
	public DespesasEventuaisDto(DespesasEventuais despesa) {
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

	public static List<DespesasEventuaisDto> converter(List<DespesasEventuais> listaDespesas) {
		List<DespesasEventuaisDto> listaDespesaDto = listaDespesas.stream()
				.map(dep -> new DespesasEventuaisDto(dep)).collect(Collectors.toList());
		
		return listaDespesaDto;
	}
}

