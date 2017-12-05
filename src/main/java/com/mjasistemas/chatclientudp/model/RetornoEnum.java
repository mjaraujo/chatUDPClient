/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

/**
 *
 * @author marcio
 */
public enum RetornoEnum {
// vc tá me ouvindo? ué 
    ENTRADA_OK("Entrada permitida"),
    ENTRADA_BANIDO("Usuário banido da sala"),
    ENTRADA_NAO_CADASTRADO("Usuário não cadastrado"),
    ERRO_SIZE("Tamanho da mensagem incorreto"),
    LOGIN_ERRO_RG("Usuario não registrado"),
    LOGIN_ERRO_SENHA("Senha inválida"),
    LOGIN_OK("Login com sucesso"),
    MENSAGEM_OK("Mensagem enviada com sucesso"),
    MENSAGEM_FALHA("Erro ao enviar mensagem"),
    MENSAGEM_TIMEOUT("Tempo de envio de mensagem excedido"),
    MENSAGEM_DESTINATARIO_INEXISTENTE("Destinatário inexistente"),
    LISTAR_SALAS_OK("Salas listadas com sucesso");
    
    private final String descricao;

    private RetornoEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao; //To change body of generated methods, choose Tools | Templates.
    }

}
