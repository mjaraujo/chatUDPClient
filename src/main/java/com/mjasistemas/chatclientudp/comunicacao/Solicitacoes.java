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
 *
 * @author marcio
 */
public class Solicitacoes {

    UDPCliente udpc;

    public Solicitacoes() {
        udpc = new UDPCliente(Configuracoes.getIP(), Configuracoes.getPorta());
    }

    public Pessoa solicitarLogin(String username, String senha) {
        Pessoa p = null;
        String msg = "00";
        msg += String.format("%12s", username);
        msg += String.format("%20s", senha);

        udpc.enviar(msg);
        udpc.run();

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
                        p.setId(-1);
                        return p;
                    case LOGIN_ERRO_SENHA:
                        p.setId(-2);
                        return p;
                }

            }

        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return p;

    }

    public List<Sala> solicitarSalasAberas(String username) {
        String msg = "01";
        msg += String.format("%12s", username);

        udpc.enviar(msg);
        udpc.run();

        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {

                switch (udpc.getRetornoSolicitacao()) {
                    case LISTAR_SALAS_OK: //login sucesso
                        Integer.parseInt(udpc.getStrRetorno().substring(3, 4));
                        String usuario = udpc.getStrRetorno().substring(7, 18).trim();
                        int tipo = Integer.parseInt(udpc.getStrRetorno().substring(19, 19));
                        switch (tipo) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                        break;
                    case LOGIN_ERRO_RG:
                        
                        break;
                    case LOGIN_ERRO_SENHA:
                        
                        break;
                }

            }

        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return new ArrayList<>();

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
