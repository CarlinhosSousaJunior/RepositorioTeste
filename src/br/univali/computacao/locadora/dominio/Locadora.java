/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.computacao.locadora.dominio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author 1978233
 */
public class Locadora {

    Set<Veiculo> veiculos;
    Set<Cliente> clientes;
    List<Locacao> locacoes;
    List<Marca> marcas;
    List<Modelo> modelos;

    private int CONTADORVEICULO = 0;
    private int CONTADORCLIENTE = 0;
    private int CONTADORLOCACAO = 0;

    public Locadora() {
        this.marcas = new ArrayList<>();
        this.marcas.add(new Marca("FIAT"));
        this.marcas.add(new Marca("VW"));
        this.marcas.add(new Marca("BMW"));
        this.marcas.add(new Marca("CITROEN"));

        this.modelos = new ArrayList<>();
        this.modelos.add(new Modelo("aircross", this.marcas.get(3)));
        this.modelos.add(new Modelo("C4 Grand Picasso", this.marcas.get(3)));
        this.modelos.add(new Modelo("147", this.marcas.get(0)));
        this.modelos.add(new Modelo("FUSCA", this.marcas.get(1)));
        this.modelos.add(new Modelo("UNO", this.marcas.get(0)));
        this.modelos.add(new Modelo("M3", this.marcas.get(2)));
        this.modelos.add(new Modelo("X2", this.marcas.get(2)));
        this.modelos.add(new Modelo("Brasilia", this.marcas.get(1)));
        this.modelos.add(new Modelo("C3", this.marcas.get(3)));

        this.veiculos = new TreeSet<>();
        this.clientes = new TreeSet<>();
        this.locacoes = new ArrayList<>();
    }

    public boolean adicionarCliente(Cliente cliente) throws Exception {
        if (this.clientes.add(cliente)) {
            return true;
        }
        throw new Exception("Não pode haver clientes com o mesmo CPF");
    }

    public boolean adicionarVeiculo(Veiculo veiculo) throws Exception {
        if (veiculo.getValorKm() <= 0) {
            throw new Exception("O valor do km rodado não pode ser menor ou igual a zero");
        }

        if (this.veiculos.add(veiculo)) {
            return true;
        }
        throw new Exception("Não pode haver veículos com placa repetida");
    }

    public Locacao alugarVeiculo(Veiculo veiculo, Cliente cliente) throws Exception {
        for (Locacao locacao : locacoes) {
            if (locacao.getVeiculo() == veiculo && locacao.estaAtiva()) {
                throw new Exception("Um Veículo não pode ter duas locações simultâneas");
            }
        }
        this.locacoes.add(new Locacao(cliente, veiculo));
        return this.locacoes.get(this.locacoes.size() - 1);
    }

    public void finalizarLocacao(Veiculo veiculo, int kmAtual) throws Exception {
        Locacao locacao = null;
        for (int x = 0; x < this.locacoes.size(); x++) {
            if (this.locacoes.get(x) != null && this.locacoes.get(x).getVeiculo().equals(veiculo) && this.locacoes.get(x).estaAtiva()) {
                locacao = this.locacoes.get(x);
                break;
            }
        }
        if (locacao != null) {
            locacao.finalizar(kmAtual);
        }
    }

    public void finalizarLocacao(String placa, int kmAtual) throws Exception{
        for (Veiculo veiculo : this.veiculos) {
            if (veiculo != null && veiculo.getPlaca().equalsIgnoreCase(placa)) {
                this.finalizarLocacao(veiculo, kmAtual);
                break;
            }
        }
    }

    public List getModelos() {
        return modelos;
    }

    public Set getVeiculos() {
        return veiculos;
    }

    public Set getClientes() {
        return clientes;
    }

    public List getLocacoes() {
        return locacoes;
    }

    public int getContadorVeiculo() {
        return this.CONTADORVEICULO;
    }

    public int getContadorCliente() {
        return this.CONTADORCLIENTE;
    }

    public int getContadorLocacao() {
        return this.CONTADORLOCACAO;
    }

}
