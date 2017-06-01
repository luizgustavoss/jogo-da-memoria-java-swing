package com.wordpress.luizgustavoss;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * Tela do Jogo
 * 
 * @author luizgustavoss
 *
 */
public class TelaJogo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Object botaoClicadoUm, botaoClicadoDois;
	private JLabel pontosJogador, labelPontosJogador;
	private Icon imagens[], imagens8[];
	private Container container;
	private String tema;
	private Jogador objJogadorJogada;
	private int fator, qtdeAcerto, qtdeTentativas;
	private boolean primeiroClique = true;
	private boolean acertou = true;
	private MyButton botaoUm, botaoDois, posicoesFiguras[];

	
	public TelaJogo(Jogador jogador, int codigoJogo, int fatorJogo) {

		super("Jogo da Memória");

		setLocation(200, 10);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				mostrarResumoJogo();
			}
		});

		inicializarComponentesDeTela(jogador, codigoJogo, fatorJogo);
	}

	
	
	/**
	 * Inicialização dos componentes de tela
	 * 
	 * @param jogador
	 * @param codigoJogo
	 * @param fatorJogo
	 */
	private void inicializarComponentesDeTela(Jogador jogador, int codigoJogo, int fatorJogo) {
		
		fator = fatorJogo;

		objJogadorJogada = jogador;
		temaJogada(codigoJogo);
		preparaArrayImagens();
		container = getContentPane();

		container.add(configurarPainelPontos(), BorderLayout.NORTH);
		container.add(configurarBotaoSair(), BorderLayout.SOUTH);
		container.add(configurarPainelImagem());

		double d_largura = (fator == 4 ? 82.5 : 80);

		d_largura *= fator;

		int i_largura = (int) d_largura;

		setSize(i_largura, i_largura + 70);
		setVisible(true);
		setResizable(false);
	}



	/**
	 * Prepara o Array de Imagens
	 */
	private void preparaArrayImagens() {

		int posicaoNoArray, x, y;

		this.imagens8 = new Icon[(fator * fator) / 2];

		for (x = 0; x < ((fator * fator) / 2); x++) {
			this.imagens8[x] = new ImageIcon(getClass().getClassLoader().getResource(
					tema + (x + 1) + ".png"));
		}

		this.imagens = new Icon[(fator * fator)];

		for (x = 0; x < 2; x++) {
			for (y = 0; y < ((fator * fator) / 2); y++) {
				do {
					posicaoNoArray = (int) (Math.random() * (fator * fator));
				} while (this.imagens[posicaoNoArray] != null);

				this.imagens[posicaoNoArray] = imagens8[y];
			}
		}
	}
	
	

	/**
	 * Descobre o tema escolhido
	 * 
	 * @Param codTema - Código do tema escolhido
	 */
	private void temaJogada(int codigoTema) {

		switch (codigoTema) {
			case 1:
				this.tema = "imagens/temas/superheroes/";
				break;
			case 2:
				this.tema = "imagens/temas/alcoholdrinks/";
				break;
		}
	}
	

	/**
	 * Mostra o resumo do jogo ao sair
	 *
	 */
	private void mostrarResumoJogo() {

		StringBuilder resumoJogo = new StringBuilder();
		resumoJogo.append("Jogador: ").append(objJogadorJogada.obterNome()).append("\n\n");
		resumoJogo.append("Pontos: ").append(objJogadorJogada.obterPontos()).append("\n\n");
		resumoJogo.append("Quantidade de tentativas: ").append(qtdeTentativas);

		JOptionPane.showMessageDialog(null, resumoJogo.toString(), "Resumo do Jogo",
				JOptionPane.INFORMATION_MESSAGE);

		setVisible(false);
	}

	
	/**
	 * Configura o botão sair
	 * 
	 * @return o botão configurado
	 */
	private JButton configurarBotaoSair(){
		
		JButton botaoSair = new JButton(" Sair do Jogo ", configurarIconeSair());
		botaoSair.setRolloverIcon(configurarIconeSairRoll());
		botaoSair.setActionCommand("botaoSair");
		botaoSair.addActionListener(this);
		
		return botaoSair;
	}
	
	
	
	/**
	 * Configurando o painel superior
	 * 
	 * @return o painel configurado
	 */
	private JPanel configurarPainelPontos(){
		
		labelPontosJogador = new JLabel("Jogador: " + objJogadorJogada.obterNome() + " | Pontos: ");

		pontosJogador = new JLabel(String.valueOf(objJogadorJogada.obterPontos()));
		
		JPanel painelPontos = new JPanel();

		painelPontos.setBackground(Color.white);
		painelPontos.add(labelPontosJogador);
		painelPontos.add(pontosJogador);
		
		return painelPontos;
	}
	
	
	/**
	 * Configura o painel de imagem
	 * 
	 * @return o painel configurado
	 */
	private JPanel configurarPainelImagem(){
		
		JPanel painelImagens = new JPanel();		
		GridLayout grid = new GridLayout(fator, fator, 5, 5);
		painelImagens.setLayout(grid);

		posicoesFiguras = new MyButton[(fator * fator)];

		for (int cont = 0; cont < (fator * fator); cont++) {
			posicoesFiguras[cont] = new MyButton(configurarIconePadrao(), imagens[cont]);
			posicoesFiguras[cont].addActionListener(this);
			painelImagens.add(posicoesFiguras[cont]);
		}
		
		return painelImagens;
	}
	
	
	/**
     * Configura o ícone Padrão
     * 
     * @return o ícone configurado
     */
	private Icon configurarIconePadrao(){
    	Icon imagemPadrao = new ImageIcon(
				getClass().getClassLoader().getResource("imagens/jogo/standard.png"));
    	return imagemPadrao;
    }
	
	
	/**
     * Configura o ícone Sair
     * 
     * @return o ícone configurado
     */
	private Icon configurarIconeSair(){
		Icon imagemSair = new ImageIcon(getClass().getClassLoader().getResource(
			"imagens/jogo/sair.png"));
		return imagemSair;
	}
	
	
	/**
     * Configura o ícone SairRoll
     * 
     * @return o ícone configurado
     */
	private Icon configurarIconeSairRoll(){
		Icon imagemSairRoll = new ImageIcon(getClass().getClassLoader().getResource(
			"imagens/jogo/sairroll.png"));
		return imagemSairRoll;
	}
	
	
	/**
     * Listener de eventos para os botões
     */
	public void actionPerformed(ActionEvent event) {

		/*Se o evento for do botão Sair...*/
		if ("botaoSair".equals(event.getActionCommand())) {
			mostrarResumoJogo();
		}

		/*Caso contrário é evento de botões de imagem*/
		else {

			/*testa se é o primeiro clique*/
			if (primeiroClique) {

				qtdeTentativas++;

				/*testa se o jogador errou na jogada anterior*/
				if (!acertou) {

					botaoUm = (MyButton) botaoClicadoUm;
					botaoDois = (MyButton) botaoClicadoDois;

					botaoUm.setImagemPadrao();
					botaoDois.setImagemPadrao();
				}

				botaoClicadoUm = event.getSource();

				botaoUm = (MyButton) botaoClicadoUm;
				botaoUm.setImagemBotao();

				/*indica que este foi o primeiro clique*/
				primeiroClique = !primeiroClique;
			}

			/*se não for o primeiro clique*/
			else {

				botaoClicadoDois = event.getSource();
				botaoDois = (MyButton) botaoClicadoDois;

				/*verifica se o jogador clicou no mesmo botão*/
				if (botaoClicadoDois == botaoClicadoUm) {
					acertou = false;
					mostrarMensagemDeAcaoNaoPermitida();
				} else {
					
					botaoDois.setImagemBotao(); // coloca a imagem no botão

					/*compara com o primeiro botão clicado*/
					if (botaoUm.getImagemBotao().equals(
							botaoDois.getImagemBotao())) {

						acertou = true;
						qtdeAcerto++;

						/*Desabilita os botões*/
						botaoUm.setEnabled(false);
						botaoDois.setEnabled(false);

						/*incrementar pontos*/
						objJogadorJogada.incrementarPontos();
						pontosJogador.setText(String.valueOf(objJogadorJogada.obterPontos()));

						/* se foi o último par encontrado, apresenta 
						resumo do jogo e fecha a janela */
						if (qtdeAcerto == ((fator * fator) / 2)) {
							mostrarResumoJogo();
							setVisible(false);
						}
					} else {
						acertou = false; // decrementar pontos
						objJogadorJogada.decrementarPontos();
						pontosJogador.setText(String.valueOf(objJogadorJogada.obterPontos()));
					}
					primeiroClique = !primeiroClique; // indica que este foi o segundo clique
				}
			}
		}
	}


	/**
	 * Apresenta mensagem de alerta de operação não permitida
	 */
	private void mostrarMensagemDeAcaoNaoPermitida() {
		JOptionPane.showMessageDialog(null, "Ação não permitida!",
				"Ação Não Permitida", JOptionPane.WARNING_MESSAGE);
	}

}