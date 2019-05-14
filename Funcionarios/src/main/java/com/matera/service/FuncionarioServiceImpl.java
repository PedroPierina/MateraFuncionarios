package com.matera.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.matera.controller.dto.FuncionarioDTO;
import com.matera.controller.dto.FuncionarioParser;
import com.matera.exceptions.CargoNotFoundException;
import com.matera.exceptions.DepartamentoNotFoundException;
import com.matera.model.Cargo;
import com.matera.model.Departamento;
import com.matera.model.Funcionario;
import com.matera.repository.CargoRepository;
import com.matera.repository.DepartamentoRepository;
import com.matera.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	@Autowired
	private CargoRepository cargoRepo;
	@Autowired
	private DepartamentoRepository departamentoRepo;

	@Override
	public void adicionar(FuncionarioDTO funcionario) throws DepartamentoNotFoundException, CargoNotFoundException {

		Cargo cargo = findByCargo(funcionario);
		Departamento departamento = findByDepartamento(funcionario);

		Funcionario func = FuncionarioParser.parse(funcionario, cargo, departamento);

		funcionarioRepo.save(func);
	}

	@Override
	public Iterable<FuncionarioDTO> listar() {
		Iterable<Funcionario> lista = funcionarioRepo.findAll();
		List<FuncionarioDTO> listaDTO = new ArrayList<FuncionarioDTO>();
		
		for (Funcionario func : lista) {
			FuncionarioDTO funcDTO = new FuncionarioDTO();

			Optional<Cargo> cargoOp = cargoRepo.findById(func.getCargo().getId());
			Optional<Departamento> departamentoOp = departamentoRepo.findById(func.getDepartamento().getId());
			
			funcDTO = FuncionarioParser.parseDTO(func, cargoOp, departamentoOp);
			
			listaDTO.add(funcDTO);
		}
		return listaDTO;
	}

	@Override
	public ResponseEntity<FuncionarioDTO> buscaUnica(Integer id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if (func.isPresent()) {
			Funcionario existente = func.get();
			FuncionarioDTO funcDTO = new FuncionarioDTO();

			Optional<Cargo> cargoOp = cargoRepo.findById(existente.getCargo().getId());
			Optional<Departamento> departamentoOp = departamentoRepo.findById(existente.getDepartamento().getId());
			
			funcDTO = FuncionarioParser.parseDTO(existente, cargoOp, departamentoOp);
			
			return ResponseEntity.status(HttpStatus.OK).body(funcDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Override
	public ResponseEntity<FuncionarioDTO> deletar(Integer id) {
		Optional<Funcionario> func = funcionarioRepo.findById(id);
		if (func.isPresent()) {
			Funcionario existente = func.get();
			funcionarioRepo.delete(existente);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}
	
	private Departamento findByDepartamento(FuncionarioDTO funcionario) throws DepartamentoNotFoundException {
		Optional<Departamento> departamento = departamentoRepo.findByDepartamento(funcionario.getDepartamento());
		return departamento.orElseThrow(() -> new DepartamentoNotFoundException("Departamento incorreto"));
	}
	
	private Cargo findByCargo(FuncionarioDTO funcionario) throws CargoNotFoundException{
		Optional<Cargo> cargo = cargoRepo.findByCargo(funcionario.getCargo());
		return cargo.orElseThrow(() -> new CargoNotFoundException("Cargo incorreto"));
	}
}
