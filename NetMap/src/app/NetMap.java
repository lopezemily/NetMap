package app;

import modelos.Roteador;
import modelos.Terminal;
import tad.ListaDinamica;

public class NetMap {

    private ListaDinamica<Terminal> listaTerminais;
    private ListaDinamica<Roteador> listaRoteadores;

    public NetMap() {
        this.listaTerminais = new ListaDinamica<Terminal>();
        this.listaRoteadores = new ListaDinamica<Roteador>();
    }

    public void cadastrar(Roteador roteador) {
        if (roteador == null) {
            throw new NullPointerException("O roteador não pode ser nulo");
        }
        listaRoteadores.insert(roteador);
    }

    public void cadastrar(Terminal terminal) {
        if (terminal == null) {
            throw new NullPointerException("O terminal não pode ser nulo");
        }
        listaTerminais.insert(terminal);
    }

    public void removerRoteador(Roteador roteador) {
        if (roteador == null) {
            throw new NullPointerException("O roteador não pode ser nulo");
        }
        listaRoteadores.remove(roteador);
    }

    public void removerTerminal(Terminal terminal) {
        if (terminal == null) {
            throw new NullPointerException("O terminal não pode ser nulo");
        }
        listaTerminais.remove(terminal);
    }

    public void conectar(Terminal terminal, Roteador roteador) {
        if (terminal == null || roteador == null) {
            throw new NullPointerException("O roteador ou terminal não podem ser nulos");
        }
        if (listaTerminais.exist(terminal) && listaRoteadores.exist(roteador)) {
            terminal.setRoteador(roteador);
            roteador.listaTerminais.insert(terminal);
        } else {
            throw new RuntimeException("Rotedor ou terminal não estão cadastrados");
        }
    }

    public void desconectar(Terminal terminal) {
        if (terminal == null) {
            throw new NullPointerException("O terminal não pode ser nulo");
        }
        if (listaTerminais.exist(terminal)) {
            if (terminal.getRoteador() == null) {
                throw new RuntimeException("O terminal já está desconectado");
            } else {
                terminal.getRoteador().listaTerminais.remove(terminal);
                terminal.setRoteador(null);
            }
        } else {
            throw new RuntimeException("O terminal não está cadastrado");
        }
    }

    public void conectar(Roteador roteador1, Roteador roteador2) {

    }

    public void desconectar(Roteador roteador1, Roteador roteador2) {

    }

    public String calcularFrequenciaTerminal(String localizacao) {

    }

    public String calcularFrequenciaRoteador(String operadora) {

    }

    public boolean enviarPacoteDados(Terminal terminal1, Terminal terminal2) {

    }

    public String toString() {

    }
}
