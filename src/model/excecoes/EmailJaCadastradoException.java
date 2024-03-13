package model.excecoes;

public class EmailJaCadastradoException extends Exception {
	
	public String getMessage() {
		return "Email Já Cadastrado no Sistema!";
	}
}
