package com.matera.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="departamentos")
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String departamento;

//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="funcionario_departamento",
//    joinColumns={@JoinColumn(name="departamento_id", referencedColumnName="id")},
//    inverseJoinColumns={@JoinColumn(name="funcionario_id", referencedColumnName="id")})
	@OneToMany(mappedBy="departamento")
	private List<Funcionario> funcionarioList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
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
		Departamento other = (Departamento) obj;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
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

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", departamento=" + departamento + ", funcionarioList=" + funcionarioList
				+ "]";
	}
	
	
	
}
