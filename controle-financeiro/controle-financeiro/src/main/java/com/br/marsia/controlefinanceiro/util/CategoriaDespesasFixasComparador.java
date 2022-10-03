package com.br.marsia.controlefinanceiro.util;

import java.util.Comparator;

import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;

public class CategoriaDespesasFixasComparador implements Comparator<DespesasFixas>{

    @Override
	public int compare(DespesasFixas despesa1, DespesasFixas despesa2) {
		return despesa1.getCategoria().getCodigo() - despesa2.getCategoria().getCodigo();

    }
}