package com.matera.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.matera.Funcionarios.model.Funcionario;
import com.matera.connection.ConnectionFactory;
//import com.matera.repository.funcionarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Ver questão de repository

@RestController
@RequestMapping("/funcionarios")
public class ControllerFuncionario {

	// Addcionar um funcionario
	@PostMapping
	public void adicionar(@RequestBody Funcionario funcionario) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = connection.prepareStatement(
					"INSERT INTO funcionario (nome, sobrenome, salario, numeroDeDependentes, cargo, departamento)VALUES(?,?,?,?,?,?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getSobrenome());
			stmt.setDouble(3, funcionario.getSalario());
			stmt.setInt(4, funcionario.getNumeroDeDependentes());
			stmt.setString(5, funcionario.getCargo().toString());
			stmt.setString(6, funcionario.getDepartamento().toString());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection, stmt);
		}
	}

	// Listar todos os funcionarios
	@GetMapping
	public List<Funcionario> listar() {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			stmt = connection.prepareStatement("SELECT * FROM funcionario");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();

				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setNumeroDeDependentes(rs.getInt("numeroDeDependentes"));
				funcionarios.add(funcionario);
				
				String indexCargo = rs.getString("cargo");
				
				if(indexCargo.equalsIgnoreCase("GERENTE")){
					funcionario.setCargo2(1);
				}
				if(indexCargo.equalsIgnoreCase("DIRETOR")){
					funcionario.setCargo2(2);
				}
				if(indexCargo.equalsIgnoreCase("PROGRAMADOR")){
					funcionario.setCargo2(3);
				}
				if(indexCargo.equalsIgnoreCase("ESTAGIARIO")){
					funcionario.setCargo2(4);
				}
				
				String indexDepartamento = rs.getString("departamento");
				
				if(indexDepartamento.equalsIgnoreCase("ENGENHARIA")){
					funcionario.setDepartamento2(1);
				}
				if(indexDepartamento.equalsIgnoreCase("SUPORTE")){
					funcionario.setDepartamento2(2);
				}
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection, stmt, rs);
		}
		return funcionarios;
	}

	// buscar um funcionario unico
	@GetMapping("/{id}")
	public Funcionario buscaUnica(@PathVariable int id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Funcionario funcionario = new Funcionario();

		try {
			stmt = connection.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setNumeroDeDependentes(rs.getInt("numeroDeDependentes"));
				
				String indexCargo = rs.getString("cargo");
				
				if(indexCargo.equalsIgnoreCase("GERENTE")){
					funcionario.setCargo2(1);
				}
				if(indexCargo.equalsIgnoreCase("DIRETOR")){
					funcionario.setCargo2(2);
				}
				if(indexCargo.equalsIgnoreCase("PROGRAMADOR")){
					funcionario.setCargo2(3);
				}
				if(indexCargo.equalsIgnoreCase("ESTAGIARIO")){
					funcionario.setCargo2(4);
				}
				
				String indexDepartamento = rs.getString("departamento");
				
				if(indexDepartamento.equalsIgnoreCase("ENGENHARIA")){
					funcionario.setDepartamento2(1);
				}
				if(indexDepartamento.equalsIgnoreCase("SUPORTE")){
					funcionario.setDepartamento2(2);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection, stmt, rs);
		}

		return funcionario;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable int id) {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = connection.prepareStatement("DELETE FROM funcionario WHERE id = ?");
			stmt.setInt(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(connection, stmt);
		}

		return ResponseEntity.noContent().build();
	}

}
