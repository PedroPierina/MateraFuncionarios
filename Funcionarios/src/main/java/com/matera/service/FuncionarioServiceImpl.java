package com.matera.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.matera.controller.dto.FuncionarioDTO;
import com.matera.controller.dto.FuncionarioParser;
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

	private Departamento findByDepartamento(FuncionarioDTO funcionario) throws DepartamentoNotFoundException {
		Optional<Departamento> departamento = departamentoRepo.findByDepartamento(funcionario.getDepartamento());
		return departamento.orElseThrow(() -> new DepartamentoNotFoundException("Departamento incorreto"));
		// if(departamento == null){
		// throw new DepartamentoNotFoundException("Departamento incorreto");
		// }
		// return departamento;
	}

	@Override
	public void adicionar(FuncionarioDTO funcionario) throws DepartamentoNotFoundException {

		Cargo cargo = cargoRepo.findByCargo(funcionario.getCargo());
		Departamento departamento = findByDepartamento(funcionario);

		Funcionario func = FuncionarioParser.parse(funcionario, cargo, departamento);

		// Setar tudo na mao e buscar o id do cargo e departamento no banco e
		// setar eles
		func.setNome(funcionario.getNome());
		func.setSobrenome(funcionario.getSobrenome());
		func.setSalario(funcionario.getSalario());
		func.setEmail(funcionario.getEmail());
		func.setNumeroDeDependentes(funcionario.getNumeroDeDependentes());
		func.setCargo(cargoRepo.findByCargo(funcionario.getCargo()));
		func.setDepartamento(findByDepartamento(funcionario));
		System.out.println();
		funcionarioRepo.save(func);
	}

	@Override
	public Iterable<FuncionarioDTO> listar() {
		Iterable<Funcionario> lista = funcionarioRepo.findAll();

		// List<FuncionarioDTO> listaDTO = lista.stream().map(funcTemp -> {
		// Optional<Cargo> cargoOp =
		// cargoRepo.findById(funcTemp.getCargo().getId());
		// Optional<Departamento> departamentoOp =
		// departamentoRepo.findById(funcTemp.getDepartamento().getId());
		//
		// FuncionarioDTO funcionarioDTO = FuncionarioParser.parseDTO(funcTemp,
		// cargoOp, departamentoOp);
		//
		// return funcionarioDTO;
		// }).collect(Collectors.toList());

		List<FuncionarioDTO> listaDTO = new ArrayList<FuncionarioDTO>();
		for (Funcionario func : lista) {
			FuncionarioDTO funcDTO = new FuncionarioDTO();
			funcDTO.setId(func.getId());
			funcDTO.setNome(func.getNome());
			funcDTO.setSobrenome(func.getSobrenome());
			funcDTO.setSalario(func.getSalario());
			funcDTO.setEmail(func.getEmail());
			funcDTO.setNumeroDeDependentes(func.getNumeroDeDependentes());

			Optional<Cargo> cargoOp = cargoRepo.findById(func.getCargo().getId());
			if (cargoOp.isPresent()) {
				Cargo cargo = cargoOp.get();
				funcDTO.setCargo(cargo.getCargo());
			}

			Optional<Departamento> departamentoOp = departamentoRepo.findById(func.getDepartamento().getId());
			if (departamentoOp.isPresent()) {
				Departamento departamento = departamentoOp.get();
				funcDTO.setDepartamento(departamento.getDepartamento());
			}
			System.out.println(funcDTO);
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

			funcDTO.setId(existente.getId());
			funcDTO.setNome(existente.getNome());
			funcDTO.setSobrenome(existente.getSobrenome());
			funcDTO.setSalario(existente.getSalario());
			funcDTO.setEmail(existente.getEmail());
			funcDTO.setNumeroDeDependentes(existente.getNumeroDeDependentes());

			Optional<Cargo> cargoOp = cargoRepo.findById(existente.getCargo().getId());
			if (cargoOp.isPresent()) {
				Cargo cargo = cargoOp.get();
				funcDTO.setCargo(cargo.getCargo());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			Optional<Departamento> departamentoOp = departamentoRepo.findById(existente.getDepartamento().getId());
			if (departamentoOp.isPresent()) {
				Departamento departamento = departamentoOp.get();
				funcDTO.setDepartamento(departamento.getDepartamento());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

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
}
