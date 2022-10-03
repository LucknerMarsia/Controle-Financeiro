package com.br.marsia.controlefinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.marsia.controlefinanceiro.controller.dto.OrcamentoFinalDto;
import com.br.marsia.controlefinanceiro.modelo.OrcamentoFinal;
import com.br.marsia.controlefinanceiro.service.OrcamentoFinalService;



@Controller
@RequestMapping("/orcamentoFinal")
@ResponseBody
public class OrcamentoFinalController {
	
	
	@Autowired
	private OrcamentoFinalService resumoSerive;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<OrcamentoFinalDto> listarResumo(@PathVariable Integer ano, @PathVariable Integer mes){
		
		OrcamentoFinal resumo = this.resumoSerive.listar(ano, mes);
		
		OrcamentoFinalDto resumoDto = new OrcamentoFinalDto().converter(resumo);
		
		return ResponseEntity.ok(resumoDto);
		
	}

}

