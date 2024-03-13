package model.excecoes;

public class EmailDiferenteException extends Exception {
	
	public String getMessage() {
		return "Os Emails Devem Ser Iguais!";
	}
}
