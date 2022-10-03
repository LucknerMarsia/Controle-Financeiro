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

import com.br.marsia.controlefinanceiro.controller.dto.DespesasEventuaisDto;
import com.br.marsia.controlefinanceiro.controller.form.DespesasEventuaisForm;
import com.br.marsia.controlefinanceiro.exception.DespesaInvalidaException;
import com.br.marsia.controlefinanceiro.modelo.DespesasEventuais;
import com.br.marsia.controlefinanceiro.service.DespesasEventuaisService;





@RestController
@RequestMapping("/despesasEventuais")
@ResponseBody
public class DespesasEventuaisController {
	
	@Autowired
	private DespesasEventuaisService despesaService;
	
	@GetMapping
	public List<DespesasEventuaisDto> listar(@RequestParam(required = false) String descricao) {

		List<DespesasEventuais> ListaDespesas = this.despesaService.listar(descricao);
	
		List<DespesasEventuaisDto> despesasDto = DespesasEventuaisDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<DespesasEventuaisDto> listarPorAnoMes(@PathVariable Integer ano, @PathVariable long mes) {			
		List<DespesasEventuais> ListaDespesas = this.despesaService.listarPorAnoMes(ano, mes);
		
		List<DespesasEventuaisDto> despesasDto = DespesasEventuaisDto.converter(ListaDespesas);
		
		return despesasDto;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesasEventuaisDto> consultar(@PathVariable Long id) {			
		Optional<DespesasEventuais> despesaOptional = this.despesaService.consultar(id);
		
		if (despesaOptional.isPresent()) {
			return ResponseEntity.ok(new DespesasEventuaisDto(despesaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@Valid @RequestBody DespesasEventuaisForm despesaForm, UriComponentsBuilder uriBuilder){
		DespesasEventuais despesa = despesaForm.converter();
		
		try {
			this.despesaService.salvar(despesa);
			URI uri = uriBuilder.path("/despesasEventuais/{id}").buildAndExpand(despesa.getId()).toUri();
			return ResponseEntity.created(uri).body(new DespesasEventuaisDto(despesa));
		
		} catch (DespesaInvalidaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @Valid @RequestBody DespesasEventuaisForm despesaForm){
		Optional<DespesasEventuais> despesaOptional = this.despesaService.consultar(id);
		if (despesaOptional.isPresent()) {
			
			DespesasEventuais despesa = despesaForm.converter();
			despesa.setId(despesaOptional.get().getId());
		
			try {
				this.despesaService.salvar(despesa);
				return ResponseEntity.ok(new DespesasEventuaisDto(despesa));
			
			} catch (DespesaInvalidaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<DespesasEventuais> optional = this.despesaService.consultar(id);
		if (optional.isPresent()) {
			this.despesaService.deletar(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}


