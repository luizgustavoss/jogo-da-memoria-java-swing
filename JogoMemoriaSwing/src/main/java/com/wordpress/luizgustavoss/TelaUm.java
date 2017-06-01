package com.wordpress.luizgustavoss;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
/**
 * Tela inicial do Jogo
 * 
 * @author luizgustavoss
 *
 */
public class TelaUm extends JFrame implements ActionListener, ItemListener{    
 
	private static final long serialVersionUID = 1L;
	
	private Container container;
    private JComboBox<String> temasJogada;
    private int indiceTema = 1;
    private JTextField fieldNomeJogador;
    private boolean jogoFacil = false;
    private JLabel nivelDificuldade;
 
 
    /*os temas para os jogos*/
    private String nomesTemas[] = { "Super Heroes", "Alcohol Drinks"};    
 
    /*índices dos temas para os jogos*/
    private int indiceTemas[] = { 1, 2 };    
    
 
    public TelaUm(){                   
 
        super( "Bem-vindo ao Jogo da Memória!" );                
 
        /*adiciona um listener à janela*/
        addWindowListener(            
            /*classe interna anônima*/
            new WindowAdapter(){
                /*trata o evento de quando o usuário fecha a janela*/
                public void windowClosing( WindowEvent windowEvent ){
                    saidaPrograma();
                }
            }   
        );
 
        inicializarComponentesDaTela();
    }
    
    
    /**
     * Método principal
     * 
     * @param args
     */
    public static void main( String args[] ){
    	 
        TelaUm application = new TelaUm();
 
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.setLocationRelativeTo( null );
    }
    
    
    /**
     * Inicialização dos componentes de tela
     */
    private void inicializarComponentesDaTela(){
    	
    	nivelDificuldade = new JLabel("Nível de dificuldade: DIFÍCIL");
        nivelDificuldade.setForeground(Color.red);
 
        setJMenuBar(construirBarraDeMenu());
        
        /*configuração do container */
        container = getContentPane();
        container.setLayout( new BorderLayout( 5, 5 ) );
        container.setBackground( Color.lightGray );
        container.add( construirPainelVazio(), BorderLayout.SOUTH );
        container.add( configurarPainelDeDados() );    
        
        setSize( 550, 380 );
        setVisible( true );
        setResizable( false );
    }
    
    
    /**
     * Construção da barra de menus
     * 
     * @return  barra de menus configurada
     */
    private JMenuBar construirBarraDeMenu(){
    	 
    	 JMenuBar barraMenu = new JMenuBar();
    	 barraMenu.setBackground( Color.lightGray );
         barraMenu.add(construirMenuDeDificuldade());  
         return barraMenu;
    }
    
    
    /**
     * Construção do menu de seleção do nível de dificuldade
     * 
     * @return o menu configurado
     */
    private JMenu construirMenuDeDificuldade(){
    	
    	JMenu menuDificuldade = new JMenu("Nível de Dificuldade");
        menuDificuldade.setBackground( Color.lightGray );
 
        ButtonGroup dificuldadeGroup = new ButtonGroup();
 
        JRadioButtonMenuItem dificil = new JRadioButtonMenuItem("Difícil (6X6)");
        dificil.setSelected(true);
 
        dificil.addActionListener(new ActionListener(){
 
            public void actionPerformed(ActionEvent evt){
                jogoFacil = false;
                nivelDificuldade.setText("Nível de dificuldade: DIFÍCIL");
                nivelDificuldade.setForeground(Color.red);
            }
        });
 
        JRadioButtonMenuItem facil = new JRadioButtonMenuItem("Fácil (4X4)");
 
        facil.addActionListener(new ActionListener(){
 
            public void actionPerformed(ActionEvent evt){
                jogoFacil = true;
                nivelDificuldade.setText("Nível de dificuldade: FÁCIL");
                nivelDificuldade.setForeground(Color.blue);
            }
        });
 
        dificuldadeGroup.add(dificil);
        dificuldadeGroup.add(facil);
        menuDificuldade.add(dificil);
        menuDificuldade.add(facil);
        
        return menuDificuldade;
    }
    
    
    /**
     * Configuração do painel de dados (que possui o painel de cabeçalho,
       o painel central e o painel de botões
       
     * @return o painel configurado
     */
    private JPanel configurarPainelDeDados(){
    	
    	JPanel painelDados = new JPanel();
        painelDados.setLayout( new BorderLayout( 5, 5 ));
        painelDados.setBackground( Color.lightGray );
        
        painelDados.add( construirPainelVazio(), BorderLayout.EAST );  
        painelDados.add( construirPainelVazio(), BorderLayout.WEST );         
        painelDados.add( configurarPainelCabecalho(), BorderLayout.NORTH);
        painelDados.add( configurarPainelCentral() );
        
        return painelDados;
    }
    
    
    
    /**
     * Configuração de um painel vazio para ser usado no rodapé e laterais
     * 
     * @return o painel configurado
     */
    private JPanel construirPainelVazio(){
        
        
        JPanel painelRodape = new JPanel();
        painelRodape.setBackground( Color.lightGray );
        
        return painelRodape;
    }
    
    
    
    
    /**
     * Configura o ícone Jogar
     * 
     * @return o ícone configurado
     */
    private Icon configurarIconeJogar(){
    	Icon imagemJogar = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogo/jogar.png"));
    	return imagemJogar;
    }
    
    
    /**
     * Configura o ícone JogarRol
     * 
     * @return o ícone configurado
     */
    private Icon configurarIconeJogarRol(){
    	Icon imagemJogarRoll = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogo/jogarroll.png"));
    	return imagemJogarRoll;
    }
    
    
    /**
     * Configura o ícone Sair
     * 
     * @return o ícone configurado
     */
    private Icon configurarIconeSair(){
    	Icon imagemSair = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogo/sair.png"));
    	return imagemSair;
    }
    
    /**
     * Configura o ícone SairRoll
     * 
     * @return o ícone configurado
     */
    private Icon configurarIconeSairRoll(){
    	    	Icon imagemSairRoll = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogo/sairroll.png"));
    	return imagemSairRoll;
    }
    
    
    /**
     * Configuração do painel de cabeçalho
     * 
     * @return o painel configurado
     */
    private JPanel configurarPainelCabecalho(){
    	
        Icon titulo = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogo/titulo.gif"));
        JLabel texto1 = new JLabel( titulo );
        texto1.setHorizontalAlignment( SwingConstants.CENTER );        
        
        GridLayout gridCabecalho = new GridLayout( 1, 1, 10, 10 );
        
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout( gridCabecalho );
        painelCabecalho.setBackground( Color.lightGray );
        painelCabecalho.add( texto1 );
        
        return painelCabecalho;
    }
    
    
    /**
     * Configuraçã do painel central
     * 
     * @return o painel configurado
     */
    private JPanel configurarPainelCentral(){
    	
    	/*label do nome do jogador*/
    	JLabel nomeJogador = new JLabel("Informe seu nome:");
        nomeJogador.setHorizontalAlignment( SwingConstants.LEFT );
 
        /*campo do nome do jogador*/
        fieldNomeJogador = new JTextField( 15 );
        fieldNomeJogador.setHorizontalAlignment( SwingConstants.LEFT );
 
        /*label do tema da jogada*/
        JLabel temaJogada = new JLabel( "Escolha o tema:" );
        temaJogada.setHorizontalAlignment( SwingConstants.LEFT );
 
        /*ComboBox do tema da jogada*/
        temasJogada = new JComboBox<String>( nomesTemas );
        temasJogada.addItemListener( this );
        temasJogada.setMaximumRowCount( 4 );
        
        GridLayout gridDados = new GridLayout( 6, 1, 10, 10 );
        
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout( gridDados );
        painelCentro.setBackground( Color.lightGray );
        painelCentro.add( temaJogada );
        painelCentro.add( temasJogada );
        painelCentro.add( nomeJogador );
        painelCentro.add( fieldNomeJogador );
    
        painelCentro.add(nivelDificuldade);
        
        painelCentro.add(configurarPainelDeBotoes());
        
        return painelCentro;
    }
    
    
    /**
     * Configuração do painel de botões
     * 
     * @return o painel configurado
     */
    private JPanel configurarPainelDeBotoes(){
    	
    	/*botão Iniciar Jogo*/
        JButton botaoIniciarJogo = new JButton( "Iniciar Jogo", configurarIconeJogar() );
        botaoIniciarJogo.setRolloverIcon( configurarIconeJogarRol() );
        botaoIniciarJogo.setActionCommand("botaoIniciarJogo");
        botaoIniciarJogo.addActionListener( this );
        botaoIniciarJogo.setSize( 250, 15 );
        botaoIniciarJogo.setHorizontalAlignment( SwingConstants.LEFT );
 
        /*botão Sair*/
        JButton botaoSair = new JButton( "Abandonar o Jogo", configurarIconeSair() );
        botaoSair.setRolloverIcon( configurarIconeSairRoll() );
        botaoSair.setActionCommand("botaoSair");
        botaoSair.addActionListener( this );
        botaoSair.setSize( 250, 15 );
        botaoSair.setHorizontalAlignment( SwingConstants.RIGHT );
        botaoSair.setHorizontalTextPosition( SwingConstants.LEFT );
 
        /*painel de botões (inferior)*/        
        GridLayout gridBotoes = new GridLayout( 1, 2, 10, 10 );
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground( Color.lightGray );
        painelBotoes.setLayout( gridBotoes );
        painelBotoes.add( botaoSair );
        painelBotoes.add( botaoIniciarJogo );  
        return painelBotoes;
    }
    
    
    /**
     * Mensagem de finalização do jogo
     */
    private void saidaPrograma(){
 
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("\n\n");
    	
    	sb.append("\t Jogo da Memória - 2005-2017 - (Finalidade Educacional) \n");
    	sb.append("\t Autor: Luiz Gustavo Stábile de Souza \n");
    	sb.append("\t github: https://github.com/luizgustavoss/jogo-da-memoria-java-swing \n");
    	sb.append("\t post: https://luizgustavoss.wordpress.com/2009/01/15/jogo-da-memoria-em-java \n\n");
    	
    	sb.append("\t CONJUNTOS DE ÍCONES *GRATUITOS* UTILIZADOS: \n\n");
    	
    	sb.append("\t superhero \n");
    	sb.append("\t Autor: Francesco Paradiso (https://www.behance.net/Frankygraphic) \n");
    	sb.append("\t License: Free for commercial use  \n\n");
    	
    	
    	sb.append("\t Alcohol drinks \n");
    	sb.append("\t Autor: Kokota (https://www.facebook.com/Iconko-1093341900698327/) \n ");
    	sb.append("\t License: Creative Commons (Attribution 3.0 Unported) https://creativecommons.org/licenses/by/3.0/ \n\n");
    	
    	sb.append("\t Hosting \n");
    	sb.append("\t Autor: Heart Internet (www.heartinternet.co.uk) \n ");
    	sb.append("\t License: Free for commercial use  \n\n");
    	
    	
    	sb.append("\t Free Funktional Icons \n");
    	sb.append("\t Autor: Creative Freedom Ltd  (https://www.creativefreedom.co.uk/free-icons/free-icons-funktional/) \n ");
    	sb.append("\t License: Free for commercial use  \n\n");
    	
    	sb.append("\n\n");
    	
    	JTextArea telaSaida = new JTextArea();
    	telaSaida.setText(sb.toString());
 
        JOptionPane.showMessageDialog(null, telaSaida, "Informações sobre o Jogo", JOptionPane.PLAIN_MESSAGE);
        
        System.exit( 0 );
    }
    
    
    /**
     * Listener de eventos para os botões
     */
    public void actionPerformed( ActionEvent event ){
 
		/*se o evento for do botão sair*/
		if (event.getActionCommand().equals("botaoSair")) {
			saidaPrograma();
		}

		/*senão é do botão iniciar jogo*/
		else if (event.getActionCommand().equals("botaoIniciarJogo")) {

			String nomeObjJogador = fieldNomeJogador.getText();

			Jogador jogador = new Jogador(nomeObjJogador);
			
			if (jogoFacil) {
				new TelaJogo(jogador, indiceTema, 4);
			} else {
				new TelaJogo(jogador, indiceTema, 6);
			}
		}
    }
    
    
    /**
     * Listener de eventos para o combobox de temas
     */
    public void itemStateChanged( ItemEvent event ){
 
        if ( event.getSource() == temasJogada ){ 
            if ( event.getStateChange() == ItemEvent.SELECTED ){ 
                indiceTema = indiceTemas[ temasJogada.getSelectedIndex() ];
            }
        }
    }
 
}
