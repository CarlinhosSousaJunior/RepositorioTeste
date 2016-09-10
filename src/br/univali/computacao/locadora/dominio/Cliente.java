/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.computacao.locadora.dominio;

/**
 *
 * @author 1978233
 */
public class Cliente implements Comparable<Cliente> {

    private String nome;
    private String cpf;

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(String nome, String cpf) throws Exception{
            if(nome == null){
                throw new Exception("O atributo nome não pode ser null");
            }
            this.nome = nome;
            this.cpf = cpf;
        }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return (this.cpf == ((Cliente)obj).cpf);
//    }
    @Override
    public int compareTo(Cliente c) {
        return this.cpf.compareTo(c.cpf);
    }
}
