package com.matera.service;

import org.springframework.http.ResponseEntity;

import com.matera.controller.dto.FuncionarioDTO;
import com.matera.exceptions.DepartamentoNotFoundException;
import com.matera.model.Funcionario;

public interface FuncionarioService {
	void adicionar(FuncionarioDTO funcionario) throws DepartamentoNotFoundException;
	Iterable<FuncionarioDTO> listar();
	ResponseEntity<FuncionarioDTO> buscaUnica(Integer id);
	ResponseEntity<FuncionarioDTO> deletar(Integer id);
}
