package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;

import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CredenciaisInvalidasException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.EmailNaoEncontradoException;


public class AlunoServices{

	private ArrayList<Aluno> todosOsAlunos = new ArrayList<Aluno>();

	public static boolean validarEmail(AlunoDTO aluno) throws EmailInvalidoException {
		String padraoEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(padraoEmail, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(aluno.getEmail());
		if (!matcher.matches()) {
			throw new EmailInvalidoException();
		}
		return matcher.matches();
	}

	public boolean emailExiste(AlunoDTO alunoDTO) throws EmailJaCadastradoException {
		for (Aluno a : todosOsAlunos) {
			if (!a.getMatricula().equals(alunoDTO.getMatricula())) {
				if (a.getEmail().equalsIgnoreCase(alunoDTO.getCEmail())) {
					throw new EmailJaCadastradoException();
				}
			}
		}
		return false;
	}

	public boolean verificarMatricula(AlunoDTO alunoDTO) throws AlunoJaMatriculadoException {
		Aluno a = new Aluno(alunoDTO);
		for (Aluno aluno: todosOsAlunos) {
			if (aluno != a) {
				if (aluno.getMatricula().equals(alunoDTO.getMatricula())){
					throw new AlunoJaMatriculadoException();
				}
			}

		}return false;
	}

	public String recuperarSenhaPeloEmail (AlunoDTO aluno) throws EmailNaoEncontradoException {
		for (Aluno a: todosOsAlunos) {
			if (a.getEmail().equalsIgnoreCase(aluno.getEmail())) {
				return a.getSenha();
			}
		}
		throw new EmailNaoEncontradoException();
	}



	public AlunoDTO login(AlunoDTO aluno) throws CredenciaisInvalidasException {

		for(Aluno a: getTodosOsAlunos()) {
			if (a.getEmail().equalsIgnoreCase(aluno.getEmail()) && a.getSenha().equals(aluno.getSenha())) {
				aluno = new AlunoDTO();
				aluno.setNome(a.getNome());
				aluno.setEmail(a.getEmail());
				aluno.setSenha(a.getSenha());
				aluno.setMatricula(a.getMatricula());
				aluno.setSexo(a.getSexo());
				
				return aluno;
			}
		}throw new CredenciaisInvalidasException();
	}
	

	public void mandarSenhaPorEmail(AlunoDTO aluno) throws EmailNaoEncontradoException, EmailException{
		String senha = recuperarSenhaPeloEmail(aluno);
		Mensageiro.enviarEmail(aluno.getEmail(), "Recuperação de Senha" ,"Sua senha atual é: " + senha);
	}
	
	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}

	public void setTodosOsAlunos(ArrayList<Aluno> alunos) {
		todosOsAlunos = alunos;
	}

}