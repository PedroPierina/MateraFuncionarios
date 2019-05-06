package com.matera.controller;

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
	@Autowired
	private funcionarioRepository funcionarios;

	// Addcionar um funcionario
	@PostMapping
	public Funcionario adicionar(@RequestBody Funcionario funcionario) {
		return funcionarios.save(funcionario);
	}

	// Listar todos os funcionarios
	@GetMapping
	public List<Funcionario> listar() {
		return funcionarios.findAll();
	}

	// buscar um funcionario unico
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> buscaUnica(@PathVariable Long id) {
		Funcionario funcionario = funcionarios.getOne(id);

		// Errro 404
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		// Codigo 200(found)
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Funcionario funcionario = funcionarios.getOne(id);

		// Errro 404
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		
		funcionarios.delete(funcionario);
		
		return ResponseEntity.noContent().build();
	}

}
