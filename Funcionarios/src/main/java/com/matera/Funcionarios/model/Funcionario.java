package com.matera.Funcionarios.model;

public class Funcionario {
	private int id;
	private String nome;
	private String sobrenome;
	private Double salario;
	private int numeroDeDependentes;
	private Cargo cargo;
	private Departamento departamento;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public int getNumeroDeDependentes() {
		return numeroDeDependentes;
	}
	public void setNumeroDeDependentes(int numeroDeDependentes) {
		this.numeroDeDependentes = numeroDeDependentes;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public void setCargo2(int index) {
		switch(index){
		case 1:
			this.cargo = Cargo.GERENTE;
			break;
		case 2:
			this.cargo = Cargo.DIRETOR;
			break;
		case 3:
			this.cargo = Cargo.PROGRAMADOR;
			break;
		case 4:
			this.cargo = Cargo.ESTAGIARIO;
		}
		
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public void setDepartamento2(int index) {
		switch(index){
		case 1:
			this.departamento = Departamento.ENGENHARIA;
			break;
		case 2:
			this.departamento = Departamento.SUPORTE;
			break;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numeroDeDependentes;
		result = prime * result + ((salario == null) ? 0 : salario.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}
	
	//Ver qual vai ser o criterio
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (cargo != other.cargo)
			return false;
		if (departamento != other.departamento)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroDeDependentes != other.numeroDeDependentes)
			return false;
		if (salario == null) {
			if (other.salario != null)
				return false;
		} else if (!salario.equals(other.salario))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}
	
	
}
