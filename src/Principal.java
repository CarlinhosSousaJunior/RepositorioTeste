
import br.univali.computacao.locadora.dominio.Cliente;
import br.univali.computacao.locadora.dominio.Locadora;
import br.univali.computacao.locadora.dominio.Veiculo;
import br.univali.computacao.locadora.view.InterfaceCliente;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1978233
 */
public class Principal {

    private Locadora locadora;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Principal principal = new Principal();
        //principal.testarSistema();
        
        InterfaceCliente view = new InterfaceCliente(new Locadora());
        Scanner ler = new Scanner(System.in);
        int operacao;
        do{
            view.printMenu();
            operacao = ler.nextInt();
            view.action(operacao);
        }while(operacao != 9);
            view.printExit();
    }
    
    
//    public void testarSistema(){
//        this.locadora = new Locadora();
//        this.locadora.adicionarCliente(new Cliente("Maria"));
//        this.locadora.adicionarCliente(new Cliente("Joaquim"));
//        this.locadora.adicionarCliente(new Cliente("Barbosa"));
//        
//        this.locadora.adicionarVeiculo(new Veiculo(this.locadora.getModelos()[0], 1000, 2015, "MQT-1332", 10.00));
//        this.locadora.adicionarVeiculo(new Veiculo(this.locadora.getModelos()[5], 10, 2016, "BMW-5511", 8.00));
//        
//
//        this.locadora.alugarVeiculo(this.locadora.getVeiculos()[0], this.locadora.getClientes()[0]);
//        this.locadora.alugarVeiculo(this.locadora.getVeiculos()[1], this.locadora.getClientes()[3]);
//        
//        this.locadora.finalizarLocacao(this.locadora.getVeiculos()[0], 1050);
//        this.locadora.finalizarLocacao(this.locadora.getVeiculos()[1], 2600);
//    }
    
    
}
