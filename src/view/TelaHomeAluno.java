package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import model.Aluno;
import model.dto.AlunoDTO;
import view.fabricacomponentes.FabricaIcones;
import view.fabricacomponentes.FabricaJButton;
import view.fabricacomponentes.FabricaJLabel;
import view.fabricacomponentes.FabricaJMenuBar;
import view.fabricacomponentes.FabricaJOptionPane;

public class TelaHomeAluno extends TelaPadrao{

	public TelaHomeAluno() {
		super("TELA INICIAL - ALUNO");
		configurarComponentes();
		setVisible(true);
	}

	public void configurarComponentes() {
		adicionarLabels();
		adicionarMenuBar();
		adicionarButtons();
		adicionarIcones();
	}
	

	private void adicionarLabels() {
		JLabel lTitulo = FabricaJLabel.criarJLabel("MENU", 440, 200, 200, 30, Color.BLACK, 30);
		add(lTitulo);
	}

	private void adicionarMenuBar() {
		JMenuBar mOpcoes = FabricaJMenuBar.MenuAluno(this);
		setJMenuBar(mOpcoes);
	}
	
	private void adicionarButtons() {
		
		JButton bEditarInformacoes = FabricaJButton.criarJButton("Editar Informações", 325, 415, 250, 40, Color.GREEN, Color.WHITE, 12);
		bEditarInformacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AlunoDTO aluno = (AlunoDTO) getAlunoController().getUsuario();
				new TelaEditarInformacoesAluno(aluno);
			}
		});
		add(bEditarInformacoes);
		
		JButton bExcluirConta = FabricaJButton.criarJButton("Excluir Conta", 325, 470, 250, 40, Color.GREEN, Color.WHITE, 12);
		bExcluirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opc = FabricaJOptionPane.criarMsgDeOpcao("Confirmação", "Deseja excluir sua conta?");
				
				if (opc == JOptionPane.YES_OPTION) {
					AlunoDTO aluno = (AlunoDTO) getAlunoController().getUsuario();
					if (getAlunoController().excluirAluno(aluno)) {
						FabricaJOptionPane.criarMsgValido("Conta excluída com sucesso!");
						dispose();
						new TelaLogin();
					}
					
				}
			}
		});
		add(bExcluirConta);
		
		JButton bSair = FabricaJButton.criarJButton("Sair", 325, 525, 250, 40, Color.GREEN, Color.WHITE, 12);
		bSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin();
			}
		});
		add(bSair);
	}

	private void adicionarIcones() {
		JLabel iconeIf = FabricaIcones.criarIcone(FabricaImagens.IF, 350, 170, 70, 94);
		add(iconeIf);
		
		JLabel imagemFundo = FabricaIcones.criarIcone(FabricaImagens.TELA_LOGIN, 0, 0, 900, 800);
		add(imagemFundo);
	}
	public static void main(String[] args) {
		new TelaHomeAluno();
	}
}
