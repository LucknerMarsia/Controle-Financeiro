package com.br.marsia.controlefinanceiro.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.marsia.controlefinanceiro.modelo.Categoria;
import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;


public class DespesasFixasForm {
	
	@NotNull(message = "Descricao é Obrigatoria") @NotEmpty @Length(min = 3)
	private String descricao;
	
	@NotNull(message = "Valor é Obrigatorio")
	private BigDecimal valor;
	
	@NotNull(message = "Data é Obrigatoria")
	private LocalDate data;
	
	private Categoria categoria;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public void setCategoria (Categoria categoria) {
		this.categoria = categoria;
	}
	public DespesasFixas converter() {
		return new DespesasFixas(this.descricao, this.valor, this.data, this.categoria);
	}
	
	

}

