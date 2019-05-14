package com.matera.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matera.model.Funcionario;

@Repository 
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{
	
//	List<Funcionario> findAll();
	
}
