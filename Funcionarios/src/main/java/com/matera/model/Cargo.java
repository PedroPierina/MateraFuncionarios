package com.matera.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cargos")
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String cargo;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="funcionario_cargo",
//    joinColumns={@JoinColumn(name="cargo_id", referencedColumnName="id")},
//    inverseJoinColumns={@JoinColumn(name="funcionario_id", referencedColumnName="id")})
	@OneToMany(mappedBy="cargo")
	private List<Funcionario> funcionarioList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	@Override
	public String toString() {
		return "Cargo [id=" + id + ", cargo=" + cargo + ", funcionarioList=" + funcionarioList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((funcionarioList == null) ? 0 : funcionarioList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (funcionarioList == null) {
			if (other.funcionarioList != null)
				return false;
		} else if (!funcionarioList.equals(other.funcionarioList))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
