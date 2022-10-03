package com.br.marsia.controlefinanceiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;



@Repository
public interface ReceitaMensalRepository extends JpaRepository<ReceitaMensal, Long> {
	
	
	@Query("SELECT r FROM ReceitaMensal r WHERE r.descricao = :descricao AND r.data >= :dtini AND r.data <= :dtfim")
	List<ReceitaMensal> buscaReceitaPorDescricaoEPeriodoDeData(String descricao, LocalDate dtini, LocalDate dtfim);


	@Query("SELECT r FROM ReceitaMensal r WHERE r.descricao = :descricao AND r.data >= :dtini AND r.data <= :dtfim AND id != :id")
	List<ReceitaMensal> buscaReceitaPorDescricaoEPeriodoDeDataEId(Long id, String descricao, LocalDate dtini, LocalDate dtfim);

	
	List<ReceitaMensal> findByDescricao(String descricao);

	
	@Query("SELECT r FROM ReceitaMensal r WHERE r.data >= :dtini AND r.data <= :dtfim")
	List<ReceitaMensal> buscaPorPeriodo(LocalDate dtini, LocalDate dtfim);

}

