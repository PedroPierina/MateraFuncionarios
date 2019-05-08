package com.matera.controller;

import java.util.ArrayList;
import java.util.List;
import com.matera.Funcionarios.model.Funcionario;
import com.matera.repository.funcionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Ver questão de repository

@RestController
@RequestMapping("/funcionarios")
public class ControllerFuncionario {
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	

	// Addcionar um funcionario
	@PostMapping
	public boolean adicionar(@RequestBody Funcionario funcionario) {
		funcionarios.add(funcionario);
		return true;
	}

//	 Listar todos os funcionarios
	@GetMapping
	public List<Funcionario> listar() {
		return funcionarios;
	}

	// buscar um funcionario unico
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscaUnica(@PathVariable int id) {
		Funcionario funcionario = funcionarios.get(id - 1);

		// Errro 404
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		// Codigo 200(found)
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id) {
		Funcionario funcionario = funcionarios.get(id - 1);

		// Errro 404
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		
		funcionarios.remove(funcionario);
		
		return ResponseEntity.noContent().build();
	}

}
