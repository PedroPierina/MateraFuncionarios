package com.matera.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.persistence.JoinColumn;

@Entity
@Table(name ="funcionarios")
public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotEmpty(message ="Nome não pode estar vazio/nulo")
	private String nome;
	
	@NotEmpty(message ="SobreNome não pode estar vazio/nulo")
	private String sobrenome;
	
	@PositiveOrZero(message = "Salario deve ser positivo")
	private Double salario;
	
	@Email(message = "Email deve ser valido")
	private String email;
	
	@Max(value = 100, message = "Numero deve ser menor do que 100")
	private int numeroDeDependentes;
	
//	@OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name="funcionario_departamento",
//    joinColumns={@JoinColumn(name="funcionario_id", referencedColumnName="id")},
//    inverseJoinColumns={@JoinColumn(name="departamento_id", referencedColumnName="id")})
//	@ManyToOne
//	@JoinColumn(name="departamento_id")
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;
	
//	@OneToOne(cascade=CascadeType.ALL)
//    @JoinTable(name="funcionario_cargo",
//    joinColumns={@JoinColumn(name="funcionario_id", referencedColumnName="id")},
//    inverseJoinColumns={@JoinColumn(name="cargo_id", referencedColumnName="id")})
	@ManyToOne
	@JoinColumn(name="cargo_id")
	private Cargo cargo;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
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
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}

