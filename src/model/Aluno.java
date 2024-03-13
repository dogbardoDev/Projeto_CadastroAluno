package model;

import model.dto.AlunoDTO;

public class Aluno{
	
	private AlunoDTO alunoDTO = new AlunoDTO();
	
	private String nome;
	private String email;
	private String senha;
	private String matricula;
	private Sexo sexo;
	
	public Aluno(AlunoDTO alunoDTO) {
		nome = alunoDTO.getNome();
		email = alunoDTO.getEmail();
		senha = alunoDTO.getSenha();
		matricula = alunoDTO.getMatricula();
		sexo = alunoDTO.getSexo();
	}
	
	public Aluno(){
		
	}
	
	public String toString() {
		return "Nome: " + getNome() + "\n" +
				"Sexo: " + getSexo().toString().toLowerCase() + "\n" +
				"Matricula: " + getMatricula() + "\n" +
				"Email: " + getEmail() + "\n";
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String m) {
		matricula = m;
	}
}