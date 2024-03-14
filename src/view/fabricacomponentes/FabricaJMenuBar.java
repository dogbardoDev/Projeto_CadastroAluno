package view.fabricacomponentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AlunoController;
import model.Aluno;
import model.dto.AlunoDTO;
import view.FabricaImagens;
import view.TelaEditarInformacoesAluno;
import view.TelaHomeAluno;
import view.TelaLogin;
import view.TelaPadrao;
import view.TelaVisualizarAluno;

public class FabricaJMenuBar{
	
	public static JMenuBar MenuAluno(TelaPadrao tela) {
		AlunoController alunoController = new AlunoController();
		JMenuBar barraAluno = new JMenuBar();
		
		JMenu mOpcoes = new JMenu("Opções");
		JMenuItem inicio = new JMenuItem("Início", FabricaImagens.INICIO);
		mOpcoes.add(inicio);
		inicio.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaHomeAluno();
			}
		});
		JMenuItem aluno = new JMenuItem("Minhas Informações", FabricaImagens.LISTAR);
		mOpcoes.add(aluno);
		aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				AlunoDTO aluno = (AlunoDTO) alunoController.getUsuario();
				new TelaVisualizarAluno(aluno);
			}
		});
		
		JMenuItem todosAlunos = new JMenuItem("Editar Informações", FabricaImagens.LISTAR);
		mOpcoes.add(todosAlunos);
		todosAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				AlunoDTO aluno = (AlunoDTO) alunoController.getUsuario();
				new TelaEditarInformacoesAluno(aluno);
			}
		});
		
		JMenuItem sair = new JMenuItem("Sair", FabricaImagens.SAIR);
		mOpcoes.add(sair);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
				new TelaLogin();
			}
		});
		barraAluno.add(mOpcoes);
		
		return barraAluno;
		
	}
	
}