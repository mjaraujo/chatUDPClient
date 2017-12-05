/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.view;

import com.mjasistemas.chatclientudp.comunicacao.Solicitacoes;
import com.mjasistemas.chatclientudp.model.RetornoEnum;
import com.mjasistemas.chatclientudp.model.Sala;
import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import com.mjasistemas.chatclientudp.model.pessoa.TipoPessoaEnum;
import com.mjasistemas.chatclientudp.model.pessoa.Usuario;

import java.util.ArrayList;

import javafx.collections.transformation.SortedList;

import javax.swing.JOptionPane;

import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 * @author marcio
 */
public class ChatForm extends javax.swing.JFrame {

    private ObservableList<Pessoa> pessoas;
    private Sala sala;
    private Pessoa pessoa;
    private Pessoa pessoaSelecionada;

    /**
     * Creates new form ChatForm
     */
    public ChatForm(Sala sala, Pessoa pessoa) {
        this.sala = sala;
        this.pessoa = pessoa;
        this.pessoas = ObservableCollections.observableList(new ArrayList<>());
        initComponents();
        iniciar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtMensagem = new javax.swing.JTextArea();
        btEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Sala:");

        jLabel2.setText("Salas abertas");

        tblSalas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane2.setViewportView(tblSalas);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${pessoas}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblUsuarios);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nickName}"));
        columnBinding.setColumnName("Usuários");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pessoaSelecionada}"), tblUsuarios, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(tblUsuarios);

        jtMensagem.setColumns(20);
        jtMensagem.setRows(5);
        jScrollPane4.setViewportView(jtMensagem);

        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btEnviar))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btEnviar))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(26, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:

        if (jtMensagem.getText() != null && pessoaSelecionada != null) {
            RetornoEnum resSolicitarMensagem;
            do {

                resSolicitarMensagem = new Solicitacoes().solicitarEnvioMensagem(pessoa.getNickName(),
                        pessoaSelecionada.getNickName(), sala.getId(), jtMensagem.getText());

            } while (resSolicitarMensagem == RetornoEnum.ERRO_SIZE);
        }
    }//GEN-LAST:event_btEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jtMensagem;
    private javax.swing.JTable tblSalas;
    private javax.swing.JTable tblUsuarios;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the pessoas
     */
    public ObservableList<Pessoa> getPessoas() {
        return pessoas;
    }

    /**
     * @param pessoas the pessoas to set
     */
    public void setPessoas(ObservableList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    private void manterChatCliente() {

    }

    private void atualizarLista() {
        do {
            pessoas.clear();
            pessoas.addAll(new Solicitacoes().solicitarLogadosSala(this.pessoa.getNickName(), sala.getId()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);

    }

    private void iniciar() {
        Thread queryThread = new Thread() {
            public void run() {
                atualizarLista();
            }
        };
        queryThread.start();
    }

    private void manterChat() {
        Thread queryThreadChat = new Thread() {
            public void run() {
                manterChatCliente();
            }
        };
        queryThreadChat.start();
    }

    /**
     * @return the pessoaSelecionada
     */
    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    /**
     * @param pessoaSelecionada the pessoaSelecionada to set
     */
    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

}
