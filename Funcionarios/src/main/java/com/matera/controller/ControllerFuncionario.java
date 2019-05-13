package com.matera.controller;


import com.matera.model.Funcionario;
import com.matera.service.FuncionarioService;

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

@RestController
@RequestMapping("/funcionarios")
public class ControllerFuncionario {
	@Autowired
	private FuncionarioService funcService;

	// Addcionar um funcionario
	@PostMapping
	public void adicionar(@RequestBody Funcionario funcionario) {
		funcService.adicionar(funcionario);
	}

	// Listar todos os funcionarios
	@GetMapping
	public @ResponseBody Iterable<Funcionario> listar() {
		return funcService.listar();
	}

	// buscar um funcionario unico
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscaUnica(@PathVariable Integer id) {
		 return funcService.buscaUnica(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> deletar(@PathVariable Integer id) {
		return funcService.deletar(id);
	}
}
