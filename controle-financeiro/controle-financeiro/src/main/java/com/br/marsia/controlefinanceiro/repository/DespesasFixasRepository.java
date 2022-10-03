package com.br.marsia.controlefinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;



@Repository
public interface DespesasFixasRepository extends JpaRepository<DespesasFixas, Long> {
	
	
	@Query("SELECT d FROM DespesasFixas d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim")
	List<DespesasFixas> buscaDespesaPorDescricaoEPeriodoDeData(String descricao, LocalDate dtini, LocalDate dtfim);
	
	@Query("SELECT d FROM DespesasFixas d WHERE d.descricao = :descricao AND d.data >= :dtini AND d.data <= :dtfim AND id != :id")
	List<DespesasFixas> buscaDespesaPorDescricaoEPeriodoDeDataEId(Long id, String descricao, LocalDate dtini,
			LocalDate dtfim);
	
	List<DespesasFixas> findByDescricao(String descricao);

	
	@Query("SELECT d FROM DespesasFixas d WHERE d.data >= :dtini AND d.data <= :dtfim")
	List<DespesasFixas> buscaPorPeriodo(LocalDate dtini, LocalDate dtfim);

}
