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

import com.br.marsia.controlefinanceiro.controller.dto.DespesasFixasDto;
import com.br.marsia.controlefinanceiro.controller.form.DespesasFixasForm;
import com.br.marsia.controlefinanceiro.exception.DespesaInvalidaException;
import com.br.marsia.controlefinanceiro.modelo.DespesasFixas;
import com.br.marsia.controlefinanceiro.service.DespesasFixasService;



@RestController
@RequestMapping("/despesasFixas")
@ResponseBody
public class DespesasFixasController {
	
	@Autowired
	private DespesasFixasService despesaService;
	
	@GetMapping
	public List<DespesasFixasDto> listar(@RequestParam(required = false) String descricao) {

		List<DespesasFixas> ListaDespesas = this.despesaService.listar(descricao);
	
		List<DespesasFixasDto> despesasDto = DespesasFixasDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<DespesasFixasDto> listarPorAnoMes(@PathVariable Integer ano, @PathVariable long mes) {			
		List<DespesasFixas> ListaDespesas = this.despesaService.listarPorAnoMes(ano, mes);
		
		List<DespesasFixasDto> despesasDto = DespesasFixasDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesasFixasDto> consultar(@PathVariable Long id) {			
		Optional<DespesasFixas> despesaOptional = this.despesaService.consultar(id);
		
		if (despesaOptional.isPresent()) {
			return ResponseEntity.ok(new DespesasFixasDto(despesaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody DespesasFixasForm despesaForm, UriComponentsBuilder uriBuilder){
		DespesasFixas despesa = despesaForm.converter();
		
		try {
			this.despesaService.salvar(despesa);
			URI uri = uriBuilder.path("/despesasFixas/{id}").buildAndExpand(despesa.getId()).toUri();
			return ResponseEntity.created(uri).body(new DespesasFixasDto(despesa));
		
		} catch (DespesaInvalidaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody DespesasFixasForm despesaForm){
		Optional<DespesasFixas> despesaOptional = this.despesaService.consultar(id);
		if (despesaOptional.isPresent()) {
			
			DespesasFixas despesa = despesaForm.converter();
			despesa.setId(despesaOptional.get().getId());
		
			try {
				this.despesaService.salvar(despesa);
				return ResponseEntity.ok(new DespesasFixasDto(despesa));
			
			} catch (DespesaInvalidaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<DespesasFixas> optional = this.despesaService.consultar(id);
		if (optional.isPresent()) {
			this.despesaService.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}

