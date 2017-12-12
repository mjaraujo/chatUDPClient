/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.comunicacao;

import com.mjasistemas.chatclientudp.model.Mensagem;
import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.StatusSolicitacaoEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Administrador;
import com.mjasistemas.chatclientudp.model.pessoa.Moderador;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import com.mjasistemas.chatclientudp.model.pessoa.TipoPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Usuario;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

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

    public RetornoEnum solicitarEnvioMensagem(String fromUser, String toUser, int sala, String mensgem) {
        String msg = "04";

        msg += String.format("%05d", sala); //numero %d, string %s
        msg += String.format("%12s", fromUser); //numero %d, string %s
        msg += String.format("%12s", toUser);
        msg += String.format("%200s", mensgem); //numero %d, string %s

        udpc.enviar(msg);
        udpc.run();

        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {

                return udpc.getRetornoSolicitacao();

            }

        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return RetornoEnum.MENSAGEM_TIMEOUT;

    }

    public List<Mensagem> solicitarNovasMensagem(int sala, String ultimaMensgemRecebida) throws ParseException {
        String msg = "05";
        List<Mensagem> lstMesagens = new ArrayList<>();

        msg += String.format("%05d", sala); //numero %d, string %s
        msg += String.format("%22s", ultimaMensgemRecebida);

        udpc.enviar(msg);

        udpc.run();
        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {

                int j = 6;
                switch (udpc.getRetornoSolicitacao()) {
                    case MENSAGEM_OK: //mesnsagem sucesso
                        int numMensagens = Integer.parseInt(udpc.getStrRetorno().substring(3, 6)); //pega a qtd de mensagens que tem no serv
                        for (int i = 0; i < numMensagens; i++) {

                            Mensagem men = new Mensagem();
                            //hora formatada
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            //hora formatada
                            Date time1 = format.parse(udpc.getStrRetorno().substring(j, j + 23).trim());
                            men.setTimestamp(time1);
                            //deu erro nao envia numeros
                            System.out.println("Hora envia" + time1);
                            j += 23;
                            String remetenteString = (udpc.getStrRetorno().substring(j, j + 12).trim());
                            j += 12;
                            men.setRemetenteString(remetenteString);
                            String destinatarioString = (udpc.getStrRetorno().substring(j, j + 12).trim()); //ver como esta no servidor tipo
                            j += 12;
                            men.setDestinatarioString(destinatarioString);

                            String mesagemRecebida = udpc.getStrRetorno().substring(j, j + 200).trim();
                            j += 200;
                            men.setConteudo(mesagemRecebida);

                            lstMesagens.add(men); // faz o recebimento da mensagem o copula em uma lista de mensagem
                        }
                        break;
                }
                return lstMesagens;
            }

        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return lstMesagens;

    }

    public List<Sala> solicitarSalasAberas(String username) {
        List<Sala> lstSalas = new ArrayList<>();
        String msg = "01";
        msg += String.format("%12s", username);

        udpc.enviar(msg);
        udpc.run();

        do {
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {
                int j = 5;
                switch (udpc.getRetornoSolicitacao()) {
                    case LISTAR_SALAS_OK: //login sucesso
                        int numSalas = Integer.parseInt(udpc.getStrRetorno().substring(3, 5));
                        for (int i = 0; i < numSalas - 1; i++) {
                            Sala s = new Sala();
                            int id = Integer.parseInt(udpc.getStrRetorno().substring(j, j + 5).trim());
                            j += 5;
                            s.setId(id);
                            String nome = udpc.getStrRetorno().substring(j, j + 40).trim();
                            j += 40;
                            s.setNome(nome);
                            int capacidade = Integer.parseInt(udpc.getStrRetorno().substring(j, j + 3).trim());
                            j += 3;
                            s.setCapacidade(capacidade);
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
        String msg = "02";
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

    public void enviarKeepAlive(String usuario, Integer sala) {
        boolean ret = false;
        String msg = "88";
        msg += String.format("%12s", usuario);
        msg += String.format("%05d", sala); //numeros estranhos complete do lado esquedo com mais coisas

        udpc.enviar(msg);
    }

    public List<Pessoa> solicitarLogadosSala(String usuario, Integer sala) {
        List<Pessoa> ret = new ArrayList<>();
        String msg = "03";
        msg += String.format("%12s", usuario);
        msg += String.format("%05d", sala); //numeros estranhos complete do lado esquedo com mais coisas

        udpc.enviar(msg);
        udpc.run();

        do {
            int j = 5;
            if (udpc.getStatusSolicitacao() == StatusSolicitacaoEnum.RESPONDIDA) {
                int numPessoas = Integer.parseInt(udpc.getStrRetorno().substring(3, 5));
                for (int i = 0; i < numPessoas; i++) {
                    int id = Integer.parseInt(udpc.getStrRetorno().substring(j, j + 5));
                    j += 5;
                    String nick = udpc.getStrRetorno().substring(j, j + 12);
                    j += 12;
                    Pessoa p = new Usuario();
                    p.setId(id);
                    p.setNickName(nick);
                    ret.add(p);

                }
                return ret;
            }
        } while (udpc.getStatusSolicitacao() != StatusSolicitacaoEnum.TIME_OUT);

        return ret;
    }
}
