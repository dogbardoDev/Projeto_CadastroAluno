package model.dao;

import model.Aluno;
import model.AlunoServices;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public interface DAO {
	
	void cadastrarAluno(AlunoDTO a) 
			throws EmailJaCadastradoException, AlunoJaMatriculadoException;
	
	void adicionarAluno (AlunoDTO a) throws AlunoJaMatriculadoException, EmailJaCadastradoException;

	void excluirAluno(AlunoDTO a);
	
	void editarAluno(AlunoDTO a);

}
