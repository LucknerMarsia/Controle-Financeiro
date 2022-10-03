package com.br.marsia.controlefinanceiro.util;

import java.util.Comparator;

import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;



public class CategoriaDespesasEventuaisComparador implements Comparator<DespesasEventuais>{

    @Override
	public int compare(DespesasEventuais despesa1, DespesasEventuais despesa2) {
		return despesa1.getCategoria().getCodigo() - despesa2.getCategoria().getCodigo();

    }
}