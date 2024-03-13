package view;

import javax.swing.JFrame;

import controller.AlunoController;


public  abstract class TelaPadrao extends JFrame{
	private AlunoController alunoController = new AlunoController();
	
	public TelaPadrao(String titulo) {
		setSize(900, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle(titulo);
	}

	public abstract void configurarComponentes();

	public AlunoController getAlunoController() {
		return alunoController;
	}
}
