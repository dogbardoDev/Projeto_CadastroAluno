package model.dao;

import java.util.ArrayList;
import model.Aluno;
import model.AlunoServices;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaMuitoPequenaException;

public class AlunoDAO implements DAO{

	private DB dados = new DB();
	private AlunoServices aServices = dados.recuperarDados();

	
	@Override
	public void cadastrarAluno(AlunoDTO a) throws AlunoJaMatriculadoException, EmailJaCadastradoException {
		Aluno aluno = new Aluno(a);
		adicionarAluno(aluno);
		dados.salvarDados(aServices);
		
	}
	
	@Override
	public void adicionarAluno (Aluno a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!aServices.getTodosOsAlunos().isEmpty()) {
			aServices.verificarMatricula(a.getMatricula());
			aServices.emailExiste(a.getEmail()); 
		}
		aServices.getTodosOsAlunos().add(a);
	}

	@Override
	public void excluirAluno(AlunoDTO a) {
		for (Aluno aluno: aServices.getTodosOsAlunos()) {
			if (aluno.getMatricula().equals(a.getMatricula())) {
				aServices.getTodosOsAlunos().remove(aluno);
				dados.salvarDados(aServices);
				break;
			}
		}
	}
	

	@Override
	public void editarAluno(AlunoDTO alunoDTO) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		for(Aluno aluno:aServices.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(alunoDTO.getMatricula())){
				aluno.setNome(alunoDTO.getNome());
				aluno.setEmail(alunoDTO.getCEmail());
				aluno.setSenha(alunoDTO.getCSenha());
				aluno.setMatricula(alunoDTO.getMatricula());
				aluno.setSexo(alunoDTO.getSexo());
				dados.salvarDados(aServices);
				break;
			}
		}
	}	

	public DB getDados() {
		return dados;
	}

	public AlunoServices getAlunoServices() {
		return aServices;
	}

	public ArrayList<Aluno> getAlunos() {
		return aServices.getTodosOsAlunos();
	}	

}
