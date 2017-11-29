package com.mjasistemas.chatclientudp;
import com.mjasistemas.chatclientudp.comunicacao.Configuracoes;
import com.mjasistemas.chatclientudp.view.LoginForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcio
 */
public class principal {
    public static void main(String[] args) {
        Configuracoes.setPorta(9876);
        Configuracoes.setIP("127.0.0.1");
        new LoginForm().setVisible(true);
    }
}
