package com.br.marsia.controlefinanceiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DespesasFixas implements Comparable<DespesasFixas>{
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public DespesasFixas() {
	}
	
	public DespesasFixas(String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		if (categoria == null) {
			this.categoria = Categoria.OUTRAS;
		} else {
			this.categoria = categoria;
		}
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public int compareTo(DespesasFixas despesa) {
		return this.categoria.getCodigo() - despesa.getCategoria().getCodigo();
	}


	

}

