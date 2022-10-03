package com.br.marsia.controlefinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;





@Repository
public interface DespesasEventuaisRepository extends JpaRepository<DespesasEventuais, Long> {
	
	
	@Query("SELECT d FROM DespesasEventuais d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim")
	List<DespesasEventuais> buscaDespesaPorDescricaoEPeriodoDeData(String descricao, LocalDate dtini, LocalDate dtfim);
	
	@Query("SELECT d FROM DespesasEventuais d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim AND id != :id")
	List<DespesasEventuais> buscaDespesaPorDescricaoEPeriodoDeDataEId(Long id, String descricao, LocalDate dtini,
			LocalDate dtfim);
	
	List<DespesasEventuais> findByDescricao(String descricao);

	
	@Query("SELECT d FROM DespesasEventuais d WHERE d.data >= :dtini AND d.data <= :dtfim")
	List<DespesasEventuais> buscaPorPeriodo(LocalDate dtini, LocalDate dtfim);

}

