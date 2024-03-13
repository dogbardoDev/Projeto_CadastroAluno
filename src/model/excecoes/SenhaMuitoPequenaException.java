package model.excecoes;

public class SenhaMuitoPequenaException extends Exception {
	
	public String getMessage() {
		return "A Senha Deve Ter No Minimo 8 Caracteres!";
	}
}
