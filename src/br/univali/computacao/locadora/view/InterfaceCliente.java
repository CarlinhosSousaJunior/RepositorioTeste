/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.computacao.locadora.view;

import br.univali.computacao.locadora.dominio.Cliente;
import br.univali.computacao.locadora.dominio.Locacao;
import br.univali.computacao.locadora.dominio.Locadora;
import br.univali.computacao.locadora.dominio.Marca;
import br.univali.computacao.locadora.dominio.Modelo;
import br.univali.computacao.locadora.dominio.Veiculo;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe responsável por dispor uma interface gráfica com o cliente de uma
 * locadora
 *
 * @author 6003540
 */
public class InterfaceCliente {

    private Locadora locadora;
    private Scanner ler;

    /**
     * Construtor recebe uma locadora como parametro para obter seus dados
     *
     * @param locadora
     */
    public InterfaceCliente(Locadora locadora) {
        this.locadora = locadora;
        this.ler = new Scanner(System.in);
    }

    /**
     * Imprime dados na tela
     *
     * @param <T>
     * @param message
     */
    private <T> void print(T message) {
        System.out.println(message);
    }

    public Cliente clientePorCpf(String cpf) {
        for (Cliente cliente : new ArrayList<Cliente>(locadora.getClientes())) {
            if (cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        return null;
    }

    public Veiculo veiculoPorPlaca(String placa) {
        for (Veiculo veiculo : new ArrayList<Veiculo>(locadora.getVeiculos())) {
            if (veiculo.getPlaca() == placa) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Imprime uma lista com todos os clientes cadastrados
     *
     * @return
     */
    private boolean printClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>(locadora.getClientes());
        if (locadora.getClientes().size() == 0) {
            print("Não existem itens na lista");
            return false;
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                print("--------------------------");
                print(i + " - " + clientes.get(i).getNome());
                print("--------------------------");
            }
            return true;
        }
    }

    /**
     * Imprime uma lista com todos os veiculos cadastrados
     *
     * @return
     */
    private boolean printVeiculos() {
        List<Veiculo> veiculos = new ArrayList<Veiculo>(locadora.getVeiculos());
        if (veiculos.size() == 0) {
            print("Não existem itens na lista");
            return false;
        } else {
            for (int i = 0; i < veiculos.size(); i++) {
                print("-------------------------");
                print("Numero do Carro - " + i);
                print("Modelo: " + veiculos.get(i).getModelo().getNome());
                print("Marca: " + veiculos.get(i).getModelo().getMarca().getNome());
                print("Ano: " + veiculos.get(i).getAno());
                print("Valor km rodado: " + veiculos.get(i).getValorKm());
                print("-------------------------");
            }
            return true;
        }
    }

    /**
     * Imprime o menu principal
     */
    public void printMenu() {
        System.out.println("Selecione a operação desejada:");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Veiculo");
        System.out.println("4 - Criar Locação");
        System.out.println("5 - Finalizar Locação");
        System.out.println("6 - Buscar Locações Ativas");
        System.out.println("7 - Buscar Locações Finalizadas");
        System.out.println("8 - Relação de Clientes");
        System.out.println("9 - Sair");
    }

    /**
     * Imprime uma mensagem para sair do sistema
     */
    public void printExit() {
        System.out.println("Você encerrou o sistema!");
    }

    /**
     * Lê uma string
     *
     * @return
     */
    private String readString() {
        return ler.next();
    }

    /**
     * Lê um int
     *
     * @return
     */
    private int readInt() {
        return ler.nextInt();
    }

    /**
     * Lê um double
     *
     * @return
     */
    private double readDouble() {
        return ler.nextDouble();
    }

    /**
     * Imprime uma mensagem de acordo com o parametro (utilizado para dar
     * feedback em cadastros)
     *
     * @param bool
     */
    private void writeBack(boolean bool) {
        if (bool) {
            print("Cadastro realizado com sucesso!");
        } else {
            print("Ops houve um problema, seu cadastro não foi finalizado!");
        }
    }

    /**
     * Cadastra um novo cliente
     *
     * @return
     */
    private boolean cadastraCliente() {
        try {
            print("Digite o nome do cliente:");
            String nome = readString();
            print("Digite o cpf do cliente:");
            String cpf = readString();
            if (locadora.adicionarCliente(new Cliente(nome, cpf))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            print(e.getMessage());
            return false;
        }
    }

    /**
     * Cadastra um novo veiculo
     *
     * @return
     */
    private boolean cadastraVeiculo() {
        try {
            print("Digite a Marca do Veiculo:");
            String marca = readString();
            print("Digite o nome do Veiculo:");
            String nome = readString();
            print("Digite a Quilometragem:");
            int km = readInt();
            print("Digite o Ano do Veiculo:");
            int ano = readInt();
            print("Digite a Placa:");
            String placa = readString();
            print("Digite o valor do km:");
            double kmVal = readDouble();
            if (locadora.adicionarVeiculo(new Veiculo(new Modelo(nome, new Marca(marca)), km, ano, placa, kmVal))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            print(e.getMessage());
            return false;
        }
    }

    /**
     * Cria uma nova locação
     *
     * @return
     */
    private boolean criaLocacao() {
        try {
            int cliente, veiculo;
            print("Escolha um dos veiculos listados (Digite o numero do veiculo):");
            if (printVeiculos()) {
                veiculo = readInt();
            } else {
                return false;
            }
            print("Escolha um cliente da lista (Digite o numero do cliente):");
            if (printClientes()) {
                cliente = readInt();
            } else {
                return false;
            }
            List<Veiculo> veiculos = new ArrayList<Veiculo>(locadora.getVeiculos());
            List<Cliente> clientes = new ArrayList<Cliente>(locadora.getClientes());

            locadora.alugarVeiculo(veiculos.get(veiculo), clientes.get(cliente));
            return true;
        } catch (Exception e) {
            print(e.getMessage());
            return false;
        }
    }

    /**
     * Finaliza uma locação ativa
     *
     * @return
     */
    private boolean finalizarLocacao() {
        try {
            print("Escolha como deseja finalziar a locação: ");
            print("1 - Digitar o número da placa do veículo");
            print("2 - Selecionar um veículo da lista");

            if (readInt() == 1) {
                print("Digite a placa do veículo:");
                String placa = readString();
                print("Digite a Quilometragem do veiculo:");
                int km = readInt();
                locadora.finalizarLocacao(placa, km);
                return true;
            } else {
                int veiculo;
                print("Escolha um dos veiculos listados (Digite o numero do veiculo):");
                if (printVeiculos()) {
                    veiculo = readInt();
                } else {
                    return false;
                }
                print("Digite a Quilometragem do veiculo:");
                int km = readInt();
                List<Veiculo> veiculos = new ArrayList<>(locadora.getVeiculos());
                locadora.finalizarLocacao(veiculos.get(veiculo), km);
                return true;
            }
        } catch (Exception e) {
            print(e.getMessage());
            return false;
        }
    }

    /**
     * retorna o valor total já gasto de cada cliente
     *
     * @param nome
     * @return
     */
    private int getTotalCliente(String nome) {
        List<Locacao> locacoes = locadora.getLocacoes();
        int total = 0;
        for (int i = 0; i < locacoes.size(); i++) {
            if (locacoes.get(i).getCliente().getNome() == nome) {
                total += locacoes.get(i).valorTotal();
            }
        }
        return total;
    }

    /**
     * Imprime uma lista de Clientes e o total já gasto por cada um
     *
     * @return
     */
    private boolean relacaoClientes() {
        List<Cliente> clientes = new ArrayList<>(locadora.getClientes());
        if (clientes.size() == 0) {
            print("Não existem itens na lista");
            return false;
        } else {
            print("Relacao de Clientes");
            for (int i = 0; i < clientes.size(); i++) {
                print("----------------------------------");
                print("Cliente: " + clientes.get(i).getNome() + ", cpf:" + clientes.get(i).getCpf() + " | Valor total pago: " + getTotalCliente(clientes.get(i).getNome()));
                print("----------------------------------");
            }
            return true;
        }
    }

    /**
     * Busca locações ativas ou não ativas de acordo com o status (true =
     * ativas, false = nao ativas)
     *
     * @param status
     */
    private void buscarLocacoes(boolean status) {
        List<Locacao> locacoes = locadora.getLocacoes();

        if (status) {
            print("Locações Ativas: ");
        } else {
            print("Locações Não Ativas: ");
        }
        for (int i = 0; i < locacoes.size(); i++) {
            if (locacoes.get(i).estaAtiva() == status) {
                print("------------------");
                print("Cliente: " + locacoes.get(i).getCliente().getNome());
                print("Veiculo: " + locacoes.get(i).getVeiculo().getModelo().getNome());
                if (!status) {
                    print("Valor Pago: " + locacoes.get(i).valorTotal());
                }
                print("------------------");
            }
        }
    }

    /**
     * Metodo que seleciona as operações de acordo com o numero digitado pelo
     * usuario
     *
     * @param operacao
     */
    public void action(int operacao) {
        try {
            switch (operacao) {
                case 1:
                    writeBack(cadastraCliente());
                    break;
                case 2:
                    writeBack(cadastraVeiculo());
                    break;
                case 4:
                    writeBack(criaLocacao());
                    break;
                case 5:
                    writeBack(finalizarLocacao());
                    break;
                case 6:
                    buscarLocacoes(true);
                    break;
                case 7:
                    buscarLocacoes(false);
                    break;
                case 8:
                    relacaoClientes();
                    break;
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
