/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.model.pessoa;

/**
 *
 * @author marcio
 */

public class Administrador extends Pessoa {

    private TipoPessoaEnum tipo;

    public Administrador() {
    }
/**
     * @return the tipo
     */
    public TipoPessoaEnum getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    @Override
    public void setTipo(TipoPessoaEnum tipo) {
        this.tipo = tipo;
    }
}
