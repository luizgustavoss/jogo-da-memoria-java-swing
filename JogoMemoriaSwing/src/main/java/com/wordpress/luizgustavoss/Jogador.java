package com.wordpress.luizgustavoss;

/**
 * Classe que representa o Jogador com seus atributos
 * @author luizgustavoss
 *
 */
public class Jogador{
	 
    private String nome;
    private int pontos;
 
    public Jogador(String nomeJogador){ 
        nome = (nomeJogador.equals("")? "Jogador": nomeJogador );
        pontos = 0;
    }
 
    public String obterNome(){ 
        return nome;
    }
 
    public int obterPontos(){ 
        return pontos;
    }
 
    public void incrementarPontos(){ 
        pontos += 5;
    }
 
    public void decrementarPontos(){ 
        pontos--;
    }
 
}