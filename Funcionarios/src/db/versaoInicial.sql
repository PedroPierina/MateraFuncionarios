CREATE TABLE funcionario(
   id 			int auto_increment,
   nome 		Varchar(20),
   sobrenome	Char(20),
   salario		Numeric,
   numeroDeDependentes int,
   cargo		Char(20),
   departamento	char(20),
   
   PRIMARY KEY(id)
);