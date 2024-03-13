package model.excecoes;

public class NenhumaPessoaCadastradaException extends Exception {
	
	public String getMessage() {
		return "Nenhuma pessoa cadastrada no momento!";
	}
	
}
