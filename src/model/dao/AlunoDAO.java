package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.AlunoServices;
import model.Sexo;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
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
		for(int i = 0; i < aServices.getTodosOsAlunos().size(); i++) {
			if(aServices.getTodosOsAlunos().get(i).getMatricula().equals(alunoDTO.getMatricula())){
				Aluno a = aServices.getTodosOsAlunos().get(i);
				a.setNome(alunoDTO.getNome());
				a.setEmail(alunoDTO.getEmail());
				a.setSenha(alunoDTO.getSenha());
				a.setMatricula(alunoDTO.getMatricula());
				a.setSexo(alunoDTO.getSexo());
				aServices.getTodosOsAlunos().set(i, a);
				dados.salvarDados(aServices);
				break;
			}
		}
	}	

	@Override
	public String recuperarEmailPorMatricula(AlunoServices central, String matricula) {
		for(Aluno aluno: central.getTodosOsAlunos()) {
			if(aluno.getMatricula().equals(matricula)) {
				return aluno.getEmail();
			}
		}
		return null;
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
