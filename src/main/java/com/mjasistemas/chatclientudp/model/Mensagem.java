/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcio
 */
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
   
    private String destinatario;
    private String remetente;
    private String conteudo;
    private Date timestamp;
    private Sessao sessao;

    public Mensagem() {
    }

    public Mensagem(Integer id) {
        this.id = id;
    }

    public Mensagem(Integer id, String destinatario, String remetente, String conteudo, Timestamp timestamp) {
        this.id = id;
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.conteudo = conteudo;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    

    public String getDestinatarioString() {
        return destinatario;
    }

    public void setDestinatarioString(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetenteString() {
        return remetente;
    }

    public void setRemetenteString(String remetente) {
        this.remetente = remetente;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    public String toString() {
        return "com.mjasistemas.chatclientudp.model.Mensagem[ id=" + id + " ]";
    }

}
