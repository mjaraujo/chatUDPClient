/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;

/**
 *
 * @author marcio
 */
public class Banidos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String tipo;
    private Integer pessoa;
    private Integer sala;

    public Banidos() {
    }

    public Banidos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }


    @Override
    public String toString() {
        return "com.mjasistemas.chatclientudp.model.Banidos[ id=" + id + " ]";
    }
    
}
