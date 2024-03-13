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
	private ArrayList<Aluno> alunos = aServices.getTodosOsAlunos();

	
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
		alunos.remove(a);
		dados.salvarDados(aServices);
	}

	@Override
	public void editarAluno(AlunoDTO alunoDTO) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException {
		for(int i = 0; i < alunos.size(); i++) {
			if(alunos.get(i).getMatricula().equals(alunoDTO.getMatricula())){
				Aluno a = alunos.get(i);
				a.setEmail(alunoDTO.getEmail());
				a.setNome(alunoDTO.getNome());
				a.setSenha(alunoDTO.getSenha());
				a.setSexo(alunoDTO.getSexo());
				a.setMatricula(alunoDTO.getMatricula());
				alunos.set(i, a);
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
		return alunos;
	}	

}
