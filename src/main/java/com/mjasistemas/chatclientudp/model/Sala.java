/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author marcio
 */
public class Sala implements Serializable {

    /**
     * @return the status
     */
    public StatusSalaEnum getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusSalaEnum status) {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private StatusSalaEnum status;
    private int capacidade;
    private String nome;

    public Sala() {
    }

    public Sala(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
