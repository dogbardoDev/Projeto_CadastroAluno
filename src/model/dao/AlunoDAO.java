package model.dao;

import java.util.ArrayList;

import controller.AlunoController;
import model.Aluno;
import model.AlunoServices;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.EmailJaCadastradoException;

public class AlunoDAO implements DAO{

	private DB dados = new DB();
	private AlunoServices aServices = dados.recuperarDados();

	
	@Override
	public void cadastrarAluno(AlunoDTO a) throws AlunoJaMatriculadoException, EmailJaCadastradoException {
		adicionarAluno(a);
		dados.salvarDados(aServices);
		
	}
	
	@Override
	public void adicionarAluno (AlunoDTO a) throws AlunoJaMatriculadoException, EmailJaCadastradoException{
		if (!aServices.getTodosOsAlunos().isEmpty()) {
			aServices.verificarMatricula(a);
			aServices.emailExiste(a); 
		}
		Aluno aluno = new Aluno(a);
		aServices.getTodosOsAlunos().add(aluno);
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
	public void editarAluno(AlunoDTO alunoDTO) {
		for(Aluno aluno:aServices.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(alunoDTO.getMatricula())){
				AlunoController.setUsuario(alunoDTO);
				aluno.setNome(alunoDTO.getNome());
				aluno.setEmail(alunoDTO.getCEmail());
				aluno.setSenhaAntiga(aluno.getSenha());
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
