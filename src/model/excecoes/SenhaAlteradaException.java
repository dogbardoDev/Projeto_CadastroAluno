package model.excecoes;

public class SenhaAlteradaException extends Exception {
	public String getMessage() {
		return "Sua Senha Foi Alterada!";
		}
}
