package com.matera.service;

import org.springframework.http.ResponseEntity;

import com.matera.controller.dto.FuncionarioDTO;
import com.matera.exceptions.CargoNotFoundException;
import com.matera.exceptions.DepartamentoNotFoundException;
import com.matera.model.Funcionario;

public interface FuncionarioService {
	void adicionar(FuncionarioDTO funcionario) throws DepartamentoNotFoundException, CargoNotFoundException;
	Iterable<FuncionarioDTO> listar();
	ResponseEntity<FuncionarioDTO> buscaUnica(Integer id);
	ResponseEntity<FuncionarioDTO> deletar(Integer id);
}
