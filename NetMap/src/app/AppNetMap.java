package app;

import modelos.Roteador;
import modelos.Terminal;

public class AppNetMap {

    public static void main(String[] args) {
        //Criando nova rede de dados
        NetMap netMap = new NetMap();
        
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
        
        netMap.desconectar(r3,r5);
        System.out.println("O terminal t1 está conectado com t5? " + netMap.enviarPacoteDados(t1, t5));
    }
        
}
