package controller;

import model.Aluno;
import model.AlunoServices;
import model.Sexo;
import model.dao.AlunoDAO;
import model.dao.DB;
import model.dto.AlunoDTO;
import model.excecoes.AlunoJaMatriculadoException;
import model.excecoes.CamposVaziosException;
import model.excecoes.EmailDiferenteException;
import model.excecoes.EmailInvalidoException;
import model.excecoes.EmailJaCadastradoException;
import model.excecoes.SenhaDiferenteException;
import model.excecoes.SenhaMuitoPequenaException;

public class AlunoController {
	
	private static AlunoDTO usuario;
	private AlunoServices aServices;
	private AlunoDAO alunoDAO;
	
	public AlunoController() {
		this.alunoDAO = new AlunoDAO();
		aServices = alunoDAO.getAlunoServices();
	}
	
	public void cadastrarAluno(AlunoDTO a)
			throws EmailInvalidoException,EmailDiferenteException, SenhaMuitoPequenaException, SenhaDiferenteException, CamposVaziosException, AlunoJaMatriculadoException, EmailJaCadastradoException {
		String nome = a.getNome().trim();;
		String email = a.getEmail().trim();
		String email2 = a.getCEmail().trim();
		String senha = a.getSenha().trim();
		String senha2 = a.getCSenha().trim();
		String matricula = a.getMatricula().trim();
		
		if(nome.isBlank() || email.isBlank() || email2.isBlank() || senha.isBlank() || senha2.isBlank() || matricula.isBlank()) {
			throw new CamposVaziosException();
		}else if (!email.equals(email2)) {
			throw new EmailDiferenteException();
		}else if(senha.length() < 8){
			throw new SenhaMuitoPequenaException();
		}else if (!senha.equals(senha2)) {
			throw new SenhaDiferenteException();
		} else{
			AlunoServices.validarEmail(email);
			alunoDAO.cadastrarAluno(a);
		}
	}
	
	public boolean excluirAluno(AlunoDTO a) {
		for (Aluno aluno: alunoDAO.getAlunos()) {
			if (aluno.getMatricula().equals(a.getMatricula())){
				alunoDAO.excluirAluno(a);
				return true;
			}
		}
		return false;
	}
	
	public void editarAluno(AlunoDTO a) 
			throws EmailInvalidoException, SenhaMuitoPequenaException, CamposVaziosException, EmailJaCadastradoException, AlunoJaMatriculadoException{
		
		if(a.getNome().isBlank() || a.getCEmail().isBlank() || a.getCSenha().isBlank()) {
			throw new CamposVaziosException();
		}else if(a.getCSenha().length() < 8){
			throw new SenhaMuitoPequenaException();
		}
		aServices.emailExiste(a);
		AlunoServices.validarEmail(a.getCEmail());
		
		alunoDAO.editarAluno(a);
		
		
	}
	
	public String recuperarEmailPorMatricula(AlunoServices central, String matricula) {
		return alunoDAO.recuperarEmailPorMatricula(central, matricula);
	}
	
	public AlunoDTO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(AlunoDTO u) {
		usuario = u;
	}
	
	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}
	
	public AlunoServices getAlunoServices() {
		return aServices;
	}
	
}
