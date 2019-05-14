package com.matera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.matera.model.Cargo;

@Repository 
public interface CargoRepository extends CrudRepository<Cargo, Integer>{
	Cargo findByCargo(String cargo);
}
