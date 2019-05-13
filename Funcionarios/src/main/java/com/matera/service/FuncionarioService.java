package com.matera.service;

import org.springframework.http.ResponseEntity;

import com.matera.model.Funcionario;

public interface FuncionarioService {
	void adicionar(Funcionario funcionario);
	Iterable<Funcionario> listar();
	ResponseEntity<Funcionario> buscaUnica(Integer id);
	ResponseEntity<Funcionario> deletar(Integer id);
}
