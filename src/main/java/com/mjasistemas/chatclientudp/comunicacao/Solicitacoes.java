/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.comunicacao;

import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusSolicitacaoEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Administrador;
import com.mjasistemas.chatclientudp.model.pessoa.Moderador;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import com.mjasistemas.chatclientudp.model.pessoa.TipoPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marcio
 */
public class Solicitacoes {

    UDPCliente udpc;

    public Solicitacoes() {
        udpc = UDPCliente.getInstance();
    }

    public Pessoa solicitarLogin(String username, String senha) {
        String msg = "00";
        msg += String.format("%12s", username);
        msg += String.format("%20s", senha);

        udpc.enviar(msg);
        udpc.run();
        Pessoa p = null;
        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {

                switch (udpc.getRetornoSolicitacao()) {
                    case LOGIN_OK: //login sucesso

                        int id = Integer.parseInt(udpc.getStrRetorno().substring(3, 8));
                        String usuario = udpc.getStrRetorno().substring(9, 20).trim();
                        int tipo = Integer.parseInt(udpc.getStrRetorno().substring(20, 21));
                        switch (tipo) {
                            case 0:
                                p = new Administrador();
                                break;
                            case 1:
                                p = new Moderador();
                                break;
                            case 2:
                                p = new Usuario();
                                break;
                        }
                        p.setId(id);
                        p.setNickName(username);
                        p.setSenha(senha);
                        return p;
                    case LOGIN_ERRO_RG:
                        p = new Usuario();
                        p.setId(-1);
                        return p;
                    case LOGIN_ERRO_SENHA:
                        p = new Usuario();
                        p.setId(-2);
                        return p;
                }

            }

        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return p;

    }

    public List<Sala> solicitarSalasAberas(String username) {
        List<Sala> lstSalas = new ArrayList<>();
        String msg = "01";
        msg += String.format("%12s", username);

        udpc.enviar(msg);
        udpc.run();

        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {
                int j = 4;
                udpc.setStrRetorno(udpc.getStrRetorno() + " ");
                switch (udpc.getRetornoSolicitacao()) {
                    case LISTAR_SALAS_OK: //login sucesso
                        int numSalas = Integer.parseInt(udpc.getStrRetorno().substring(2, 4));
                        for (int i = 0; i < numSalas; i++) {
                            Sala s = new Sala();
                            int id = Integer.parseInt(udpc.getStrRetorno().substring(j, j + 5).trim());
                            j += 5;
                            s.setId(id);
                            String nome = udpc.getStrRetorno().substring(j, j + 100).trim();
                            j += 100;
                            s.setNome(nome);
                            int capacidade = Integer.parseInt(udpc.getStrRetorno().substring(j, j + 5).trim());
                            j += 5;
                            s.setId(capacidade);
                            lstSalas.add(s);
                        }
                        break;
                }
                return lstSalas;
            }


        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return lstSalas;

    }

    public RetornoEnum solicitarEntrada(String usuario, Integer sala) {
        boolean ret = false;
        String msg = "1";
        msg += String.format("%05d", sala); //numeros estranhos complete do lado esquedo com mais coisas
        msg += String.format("%12s", usuario);

        udpc.enviar(msg);
        udpc.run();

        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {
                return udpc.getRetornoSolicitacao();
            }
        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return RetornoEnum.ENTRADA_NAO_CADASTRADO;
    }
}
