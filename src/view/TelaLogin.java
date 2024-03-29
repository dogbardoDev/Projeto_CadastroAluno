package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AlunoController;
import model.Aluno;
import model.dto.AlunoDTO;
import model.excecoes.CredenciaisInvalidasException;
import model.excecoes.SenhaAlteradaException;
import view.fabricacomponentes.*;

public class TelaLogin extends TelaPadrao{
	public JTextField tLogin;
	public JPasswordField tSenha;
	public TelaLogin() {
		super("LOGIN");
		
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarTextFields();
		adicionarButtons();
		adicionarcheckBox();
		adicionarIcones();
	}


	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("CADASTRO", 420, 210, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		lTitulo = FabricaJLabel.criarJLabel("MONITORES", 420, 250, 200, 30, Color.BLACK, 30);
		add(lTitulo);
		
		
		JLabel lLogin = FabricaJLabel.criarJLabel("Login", 335, 350, 50, 30, Color.BLACK, 12);
		add(lLogin);
		
		JLabel lSenha = FabricaJLabel.criarJLabel("Senha", 335, 405, 50, 30, Color.BLACK, 12);
		add(lSenha);
		
	}

	private void adicionarTextFields() {
		tLogin = FabricaJTextField.criarJTextField(370, 375, 200, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tLogin.setToolTipText("Exemplo: brunno@academico.ifpb.edu.br");
		add(tLogin);
		tSenha = FabricaJTextField.criarJPasswordField(370, 430, 200, 30, Color.WHITE, Color.BLACK, 12, Color.GRAY);
		tSenha.setToolTipText("Exemplo: brunno123");
		add(tSenha);
		
	}
	
	private void adicionarButtons() {
		JButton bLogin = FabricaJButton.criarJButton("Login", 336, 500, 234, 30, Color.GREEN, Color.WHITE, 12);
		add(bLogin);
		bLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				AlunoDTO aluno = new AlunoDTO();
				aluno.setEmail(tLogin.getText());
				aluno.setSenha(tSenha.getText()); ;
				try {
					getAlunoController();
					AlunoController.setUsuario(getAlunoController().getAlunoServices().login(aluno));
					AlunoDTO usuario = getAlunoController().getUsuario();
					if (usuario != null) {
						dispose();
						new TelaHomeAluno();
					}
				} catch (CredenciaisInvalidasException | SenhaAlteradaException e1) {
					FabricaJOptionPane.criarMsgErro(e1.getMessage());
				}
			}
		});
		
		JButton bEsqueciSenha = FabricaJButton.criarJButton("Esqueci a Senha", 336, 560, 115, 30, Color.GREEN, Color.WHITE, 12);
		add(bEsqueciSenha);
		bEsqueciSenha.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaEsqueciSenha();
			}
		});
		
		JButton bCadastrar = FabricaJButton.criarJButton("Cadastrar-se", 454, 560, 115, 30, Color.GREEN, Color.WHITE, 12);
		add(bCadastrar);
		bCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadastroAluno();
			}
		});
	}
	
	private void adicionarcheckBox() {
		
		JCheckBox boxVisualizarSenha = new JCheckBox("Visualizar Senha");
	    boxVisualizarSenha.setBounds(365, 460, 145, 30);
	    boxVisualizarSenha.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            if (boxVisualizarSenha.isSelected()) {
	                tSenha.setEchoChar((char) 0); // Exibe os caracteres
	            } else {
	                tSenha.setEchoChar('*'); // Oculta os caracteres
	            }
	        }
	    });
	    add(boxVisualizarSenha);
	}
	
	private void adicionarIcones() {
		JLabel iconeLogin = FabricaIcones.criarIcone(FabricaImagens.LOGIN, 328, 375, 50, 30);
		add(iconeLogin);
		
		JLabel iconeSenha = FabricaIcones.criarIcone(FabricaImagens.SENHA, 328, 430, 50, 30);
		add(iconeSenha);
		
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 328, 200, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	
}
