package com.matera.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matera.model.Departamento;

@Repository 
public interface DepartamentoRepository extends CrudRepository<Departamento, Integer>{
	Optional<Departamento> findByDepartamento(String departamento);
}
