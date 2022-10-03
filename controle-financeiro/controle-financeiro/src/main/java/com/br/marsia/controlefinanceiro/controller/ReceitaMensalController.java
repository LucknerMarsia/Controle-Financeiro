package com.br.marsia.controlefinanceiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.marsia.controlefinanceiro.controller.dto.ReceitaMensalDto;
import com.br.marsia.controlefinanceiro.controller.form.ReceitaMensalForm;
import com.br.marsia.controlefinanceiro.exception.ReceitaInvalidaException;
import com.br.marsia.controlefinanceiro.modelo.ReceitaMensal;
import com.br.marsia.controlefinanceiro.service.ReceitaMensalService;



@RestController
@RequestMapping("/receitaMensal")
@ResponseBody
public class ReceitaMensalController {
	
	@Autowired
	private ReceitaMensalService receitaService;
	
	@GetMapping
	public List<ReceitaMensalDto> listar(@RequestParam(required = false) String descricao) {			
		List<ReceitaMensal> ListaReceitas = this.receitaService.listar(descricao);
		
		List<ReceitaMensalDto> receitasDto = ReceitaMensalDto.converter(ListaReceitas);
		
		return receitasDto;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaMensalDto> consultar(@PathVariable Long id) {			
		Optional<ReceitaMensal> receitaOptional = this.receitaService.consultar(id);
		
		if (receitaOptional.isPresent()) {
			return ResponseEntity.ok(new ReceitaMensalDto(receitaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<ReceitaMensalDto> listarPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {			
		List<ReceitaMensal> ListaReceitas = this.receitaService.listarPorAnoMes(ano, mes);
		
		List<ReceitaMensalDto> receitasDto = ReceitaMensalDto.converter(ListaReceitas);
		
		return receitasDto;
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody ReceitaMensalForm receitaForm, UriComponentsBuilder uriBuilder){
		ReceitaMensal receita = receitaForm.converter();
		
		try {
			this.receitaService.salvar(receita);
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
			return ResponseEntity.created(uri).body(new ReceitaMensalDto(receita));
		
		} catch (ReceitaInvalidaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaMensalForm receitaForm){
		Optional<ReceitaMensal> receitaOptional = this.receitaService.consultar(id);
		if (receitaOptional.isPresent()) {
			
			ReceitaMensal receita = receitaForm.converter();
			receita.setId(receitaOptional.get().getId());
		
			try {
				this.receitaService.salvar(receita);
				return ResponseEntity.ok(new ReceitaMensalDto(receita));
			
			} catch (ReceitaInvalidaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<ReceitaMensal> optional = this.receitaService.consultar(id);
		if (optional.isPresent()) {
			this.receitaService.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

}

