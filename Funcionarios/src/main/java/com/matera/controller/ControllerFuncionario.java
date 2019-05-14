package com.matera.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.matera.controller.dto.FuncionarioDTO;
import com.matera.exceptions.DepartamentoNotFoundException;
import com.matera.model.Funcionario;
import com.matera.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class ControllerFuncionario {
	@Autowired
	private FuncionarioService funcService;

	// Addcionar um funcionario
	@PostMapping
	public ResponseEntity<FuncionarioDTO> adicionar(@RequestBody FuncionarioDTO funcionario) {
		try {
			funcService.adicionar(funcionario);
			return ResponseEntity.ok().build();
		} catch (DepartamentoNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

//	 Listar todos os funcionarios
	@GetMapping
	public @ResponseBody Iterable<FuncionarioDTO> listar() {
		return funcService.listar();
	}
//
//	// buscar um funcionario unico
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> buscaUnica(@PathVariable Integer id) {
		 return funcService.buscaUnica(id);
	}
//
	@DeleteMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> deletar(@PathVariable Integer id) {
		return funcService.deletar(id);
	}
}
