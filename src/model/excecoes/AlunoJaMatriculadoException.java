package model.excecoes;

public class AlunoJaMatriculadoException extends Exception{
	

	public String getMessage() {
		return "Aluno já matriculado";
	}
}
