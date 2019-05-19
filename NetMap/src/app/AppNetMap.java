package app;

import java.util.Scanner;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import modelos.Roteador;
import modelos.Terminal;

public class AppNetMap {

    //Criando nova rede de dados
    public static NetMap netMap = new NetMap();

    public static void main(String[] args) {
        usarNetMapFull();
    }

    public static void usarNetMapTrial() {
        //Instanciando terminais
        Terminal t1 = new Terminal("t1", "Cajamar");
        Terminal t2 = new Terminal("t2", "Jundiai");
        Terminal t3 = new Terminal("t3", "Jundiai");
        Terminal t4 = new Terminal("t4", "Jundiai");
        Terminal t5 = new Terminal("t5", "São Paulo");
        Terminal t6 = new Terminal("t6", "Cajamar");
        Terminal t7 = new Terminal("t7", "Cajamar");

        //Instanciando roteadores
        Roteador r1 = new Roteador("r1", "Vivo");
        Roteador r2 = new Roteador("r2", "Tim");
        Roteador r3 = new Roteador("r3", "Embratel");
        Roteador r4 = new Roteador("r4", "Claro");
        Roteador r5 = new Roteador("r5", "Claro");

        //Cadastrando terminais instanciados
        netMap.cadastrar(t1);
        netMap.cadastrar(t2);
        netMap.cadastrar(t3);
        netMap.cadastrar(t4);
        netMap.cadastrar(t5);
        netMap.cadastrar(t6);
        netMap.cadastrar(t7);

        //Cadastrando roteadores instanciados
        netMap.cadastrar(r1);
        netMap.cadastrar(r2);
        netMap.cadastrar(r3);
        netMap.cadastrar(r4);
        netMap.cadastrar(r5);

        //Fazendo conexões na rede criada de acordo com exemplo da documentação
        netMap.conectar(t1, r1);
        netMap.conectar(t2, r2);
        netMap.conectar(t3, r2);
        netMap.conectar(t4, r2);
        netMap.conectar(t5, r5);
        netMap.conectar(t6, r4);
        netMap.conectar(t7, r4);

        netMap.conectar(r1, r3);
        netMap.conectar(r2, r3);
        netMap.conectar(r4, r3);
        netMap.conectar(r5, r3);

        //Testes funcionais
        System.out.println(netMap);
        System.out.println("Terminais em Cajamar: " + netMap.calcularFrequenciaTerminal("Cajamar"));
        System.out.println("Operadoras com Vivo: " + netMap.calcularFrequenciaRoteador("Vivo"));
        System.out.println("O terminal t1 está conectado com t5? " + netMap.enviarPacoteDados(t1, t5));

        netMap.desconectar(r3, r5);
        System.out.println("O terminal t1 está conectado com t5? " + netMap.enviarPacoteDados(t1, t5));
    }

    public static void usarNetMapFull() {
        int opcaoEscolhida = -1;
        while (opcaoEscolhida != 0) {
            try {
                String opcao = JOptionPane.showInputDialog(null, textoMenu(), "Menu principal", INFORMATION_MESSAGE);

                opcaoEscolhida = Integer.valueOf(opcao);

                switch (opcaoEscolhida) {
                    case 1:
                        cadastrarRoteador();
                        break;
                    case 2:
                        cadastrarTerminal();
                        break;
                    case 3:
                        conectarRoteador();
                        break;
                    case 4:
                        conectarTerminal();
                        break;
                    case 5:
                        desconectarRoteador();
                        break;
                    case 6:
                        desconectarTerminal();
                        break;
                    case 7:
                        removerRoteador();
                        break;
                    case 8:
                        removerTerminal();
                        break;
                    case 9:
                        calcularFrequenciaTerminal();
                        break;
                    case 10:
                        calcularFrequenciaRoteador();
                        break;
                    case 11:
                        enviarPacoteDados();
                        break;
                    case 12:
                        exibirComponenetes();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Erro", ERROR_MESSAGE);
            }

        }

    }

    private static String textoMenu() {
        return "O que deseja fazer?\n"
                + "(1) Cadastrar roteador\n"
                + "(2) Cadastrar terminal\n"
                + "(3) Conectar roteador\n"
                + "(4) Conectar terminal\n"
                + "(5) Desconectar roteadores\n"
                + "(6) Desconectar terminal\n"
                + "(7) Remover roteador\n"
                + "(8) Remover terminal\n"
                + "(9) Calcular a frequencia de terminais por localização\n"
                + "(10) Calcular a frequencia de roteador por operadora\n"
                + "(11) Enviar pacote de dados\n"
                + "(12) Exibir componentes da rede\n"
                + "(0) Sair\n";
    }

    private static void cadastrarRoteador() {
        String nomeRoteador = JOptionPane.showInputDialog("Qual é o nome do roteador?");
        String nomeOperadora = JOptionPane.showInputDialog("Qual é o nome da operadora?");
        Roteador r = new Roteador(nomeRoteador, nomeOperadora);
        netMap.cadastrar(r);
        JOptionPane.showMessageDialog(null, "Roteador cadastrado com sucesso! =D", "Sucesso", PLAIN_MESSAGE);
    }

    private static void cadastrarTerminal() {
        String nomeTerminal = JOptionPane.showInputDialog("Qual é o nome do terminal?");
        String nomeLocalizacao = JOptionPane.showInputDialog("Qual a localizacao do terminal?");
        Terminal t = new Terminal(nomeTerminal, nomeLocalizacao);
        netMap.cadastrar(t);
        JOptionPane.showMessageDialog(null, "Terminal cadastrado com sucesso! =D", "Sucesso", PLAIN_MESSAGE);
    }

    private static void conectarRoteador() {
        String nomeRoteador1 = JOptionPane.showInputDialog("Qual é o nome do roteador 1?");
        String nomeRoteador2 = JOptionPane.showInputDialog("Qual é o nome do roteador 2?");
        Roteador roteador1 = netMap.buscarRoteador(nomeRoteador1);
        Roteador roteador2 = netMap.buscarRoteador(nomeRoteador2);
        netMap.conectar(roteador1, roteador2);
        JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void conectarTerminal() {
        String nomeRoteador = JOptionPane.showInputDialog("Qual é o nome do roteador?");
        String nomeTerminal = JOptionPane.showInputDialog("Qual é o nome do terminal?");
        Roteador roteador = netMap.buscarRoteador(nomeRoteador);
        Terminal terminal = netMap.buscarTerminal(nomeTerminal);
        netMap.conectar(terminal, roteador);
        JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void desconectarRoteador() {
        String nomeRoteador1 = JOptionPane.showInputDialog("Qual é o nome do roteador 1?");
        String nomeRoteador2 = JOptionPane.showInputDialog("Qual é o nome do roteador 2?");
        Roteador roteador1 = netMap.buscarRoteador(nomeRoteador1);
        Roteador roteador2 = netMap.buscarRoteador(nomeRoteador2);
        netMap.desconectar(roteador1, roteador2);
        JOptionPane.showMessageDialog(null, "Desconectado com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void desconectarTerminal() {
        String nomeTerminal = JOptionPane.showInputDialog("Qual é o nome do terminal?");
        Terminal terminal = netMap.buscarTerminal(nomeTerminal);
        netMap.desconectar(terminal);
        JOptionPane.showMessageDialog(null, "Desconectado com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void removerRoteador() {
        String nomeRoteador = JOptionPane.showInputDialog("Qual é o nome do roteador?");
        Roteador roteador = netMap.buscarRoteador(nomeRoteador);
        netMap.removerRoteador(roteador);
        JOptionPane.showMessageDialog(null, "Roteador removido com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void removerTerminal() {
        String nomeTerminal = JOptionPane.showInputDialog("Qual é o nome do terminal?");
        Terminal terminal = netMap.buscarTerminal(nomeTerminal);
        netMap.removerTerminal(terminal);
        JOptionPane.showMessageDialog(null, "Terminal removido com sucesso!", "Sucesso", PLAIN_MESSAGE);
    }

    private static void calcularFrequenciaTerminal() {
        String nomeLocalizacao = JOptionPane.showInputDialog("Qual a localização do terminal?");
        int total = netMap.calcularFrequenciaTerminal(nomeLocalizacao);
        JOptionPane.showMessageDialog(null, "O número de terminais em " + nomeLocalizacao + " é: " + total, "Informação", INFORMATION_MESSAGE);

    }

    private static void calcularFrequenciaRoteador() {
        String nomeOperadora = JOptionPane.showInputDialog("Qual a operadora do roteador?");
        int total = netMap.calcularFrequenciaRoteador(nomeOperadora);
        JOptionPane.showMessageDialog(null, "O número de roteadores de " + nomeOperadora + " é: " + total, "Informação", INFORMATION_MESSAGE);
    }

    private static void enviarPacoteDados() {
        String nomeTerminal1 = JOptionPane.showInputDialog("Qual é o nome do terminal 1?");
        String nomeTerminal2 = JOptionPane.showInputDialog("Qual é o nome do terminal 2?");
        Terminal terminal1 = netMap.buscarTerminal(nomeTerminal1);
        Terminal terminal2 = netMap.buscarTerminal(nomeTerminal2);
        boolean pacoteEnviado = netMap.enviarPacoteDados(terminal1, terminal2);
        if (pacoteEnviado) {
            JOptionPane.showMessageDialog(null, "Pacote enviado com sucesso!", "Sucesso", PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Pacote não enviado.", "Erro", ERROR_MESSAGE);
        }
    }

    private static void exibirComponenetes() {
        JOptionPane.showMessageDialog(null, netMap.toString(), "Componentes da rede", PLAIN_MESSAGE);
    }

}
