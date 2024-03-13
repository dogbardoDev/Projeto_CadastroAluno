package model.dto;

import model.Sexo;

public class AlunoDTO {
	private String nome;
	private String email;
	private String cemail;
	private String senha;
	private String csenha;
	private String matricula;
	private Sexo sexo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public String getCEmail() {
		return cemail;
	}
	public void setCEmail(String cemail) {
		this.cemail = cemail;
	}
	
	public String getCSenha() {
		return csenha;
	}
	public void setCSenha(String csenha) {
		this.csenha = csenha;
	}
}
