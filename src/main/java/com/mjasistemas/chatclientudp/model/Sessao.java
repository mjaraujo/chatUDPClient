/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author marcio
 */
//mesnsafgem pertence a sala

public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date inicio;
    private Date fim;
    private Sala sala;

    public Sessao() {
    }

    public Sessao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }


    @Override
    public String toString() {
        return "com.mjasistemas.chatclientudp.model.Sessao[ id=" + id + " ]";
    }
    
}
