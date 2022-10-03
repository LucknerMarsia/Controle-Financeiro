package com.br.marsia.controlefinanceiro.modelo;

public enum Categoria {
	
	

	ALIMENTACAO(0,"Alimentação"), 
	SAUDE(1,"Saúde"), 
	MORADIA(2, "Moradia"), 
	TRANSPORTE(3, "Transporte"), 
	EDUCACAO(4, "Educacao"), 
	LAZER(5, "Lazer"), 
	IMPREVISTOS(6, "Imprevistos"), 
	OUTRAS(7, "Outras");
	
	private Integer codigo;
	private String descricao;
	
	Categoria(Integer codigo, String descricao) {
		this.codigo    = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}	
