package model.excecoes;

public class NenhumEditalCadastradoExcecption extends Exception {
	public String getMessage() {
		return "Nenhum Edital Cadastrado no Momento!";
	}
}
