package com.wordpress.luizgustavoss;

import javax.swing.*;

/**
 * Especialização de um JButton para armazenar dois ícones
 * 
 * @author luizgustavoss
 *
 */
public class MyButton extends JButton{
 
	private static final long serialVersionUID = 1L;
	
	private Icon imagemPadrao;
    private Icon imagemBotao;
 
    public MyButton(Icon imagemPadrao, Icon imagemBotao){
        super(); 
        this.imagemBotao = imagemBotao;
        this.imagemPadrao = imagemPadrao;
        setImagemPadrao();
    }
 
    public void setImagemPadrao(){ 
        this.setIcon(imagemPadrao);
    }
 
    public void setImagemBotao(){ 
        this.setIcon(imagemBotao);
    }    
 
    public Icon getImagemBotao(){ 
        return this.imagemBotao;
    }
}
