package com.matera.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.matera.model.Cargo;
import com.matera.model.Departamento;
import com.matera.model.Funcionario;

public final class FuncionarioParser {
	private FuncionarioParser(){
		
	}
	
	public static Funcionario parse(FuncionarioDTO funcionario, Cargo cargo, Departamento departamento){
		Funcionario func = new Funcionario();
		
		func.setNome(funcionario.getNome());
		func.setSobrenome(funcionario.getSobrenome());
		func.setSalario(funcionario.getSalario());
		func.setEmail(funcionario.getEmail());
		func.setNumeroDeDependentes(funcionario.getNumeroDeDependentes());
		func.setCargo(cargo);
		func.setDepartamento(departamento);
		
		return func;
	}
	
	public static FuncionarioDTO parseDTO(Funcionario func, Optional<Cargo> cargo, Optional<Departamento> departamento){
		List<FuncionarioDTO> listaDTO = new ArrayList<FuncionarioDTO>();
		FuncionarioDTO funcDTO = new FuncionarioDTO();
		funcDTO.setId(func.getId());
		funcDTO.setNome(func.getNome());
		funcDTO.setSobrenome(func.getSobrenome());
		funcDTO.setSalario(func.getSalario());
		funcDTO.setEmail(func.getEmail());
		funcDTO.setNumeroDeDependentes(func.getNumeroDeDependentes());
		listaDTO.add(funcDTO);
		
		Optional<Cargo> cargoOp = cargo;
		if (cargoOp.isPresent()) {
			Cargo cargoR = cargoOp.get();
			funcDTO.setCargo(cargoR.getCargo());
		}

		Optional<Departamento> departamentoOp = departamento;
		if (departamentoOp.isPresent()) {
			Departamento departamentoR = departamentoOp.get();
			funcDTO.setDepartamento(departamentoR.getDepartamento());
		} 
		
		return funcDTO;
	}
}
