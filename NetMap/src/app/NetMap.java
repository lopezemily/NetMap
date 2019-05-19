package app;

import modelos.Roteador;
import modelos.Terminal;
import tad.ListaDinamica;

public class NetMap {

    private final ListaDinamica<Terminal> listaTerminais;
    private final ListaDinamica<Roteador> listaRoteadores;

    public NetMap() {
        this.listaTerminais = new ListaDinamica<>();
        this.listaRoteadores = new ListaDinamica<>();
    }

    public void cadastrar(Roteador roteador) {
        if (roteador == null) {
            throw new NullPointerException("O roteador não pode ser nulo");
        }
        if (listaRoteadores.exist(roteador)) {
            throw new RuntimeException("O roteador já foi cadastrado");
        }
        listaRoteadores.insert(roteador);
    }

    public void cadastrar(Terminal terminal) {
        if (terminal == null) {
            throw new NullPointerException("O terminal não pode ser nulo");
        }
        if (listaTerminais.exist(terminal)) {
            throw new RuntimeException("O terminal já foi cadastrado");
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
        if (roteador1 == null || roteador2 == null) {
            throw new NullPointerException("O roteador não pode ser nulo");
        }
        if (listaRoteadores.exist(roteador1) && listaRoteadores.exist(roteador2)) {
            roteador1.listaRoteadores.insert(roteador2);
            roteador2.listaRoteadores.insert(roteador1);
        } else {
            throw new RuntimeException("Rotedor não está cadastrado");
        }

    }

    public void desconectar(Roteador roteador1, Roteador roteador2) {
        if (roteador1 == null || roteador2 == null) {
            throw new NullPointerException("O roteador não pode ser nulo");
        }
        if (listaRoteadores.exist(roteador1) && listaRoteadores.exist(roteador2)) {
            roteador1.listaRoteadores.remove(roteador2);
            roteador2.listaRoteadores.remove(roteador1);
        } else {
            throw new RuntimeException("Rotedor não está cadastrado");
        }

    }

    public int calcularFrequenciaTerminal(String localizacao) {
        int cont = 0;
        for (int i = 0; i < listaTerminais.size(); i++) {
            Terminal t = listaTerminais.get(i);
            if (t.getLocalizacao().equals(localizacao)) {
                cont++;
            }
        }
        return cont;
    }

    public int calcularFrequenciaRoteador(String operadora) {
        int cont = 0;
        for (int i = 0; i < listaRoteadores.size(); i++) {
            Roteador r = listaRoteadores.get(i);
            if (r.getOperadora().equals(operadora)) {
                cont++;
            }
        }
        return cont;

    }

    public boolean enviarPacoteDados(Terminal terminal1, Terminal terminal2) {
        if (terminal1.roteador == null || terminal2.roteador == null) {
            return false;
        }
        ListaDinamica<Roteador> listaRoteadoresVisitados = new ListaDinamica<>();
        ListaDinamica<Roteador> listaRoteadoresParaVisitar = new ListaDinamica<>();
        listaRoteadoresParaVisitar.insert(terminal1.roteador);

        while (!listaRoteadoresParaVisitar.isEmpty()) {
            Roteador aux = listaRoteadoresParaVisitar.remove(0);
            listaRoteadoresVisitados.insert(aux);

            if (aux.listaTerminais.exist(terminal2)) {
                return true;
            } else {
                for (int i = 0; i < aux.listaRoteadores.size(); i++) {
                    Roteador r = aux.listaRoteadores.get(i);
                    if (!listaRoteadoresVisitados.exist(r)) {
                        listaRoteadoresParaVisitar.insert(r);
                    }
                }

            }
        }
        return false;

    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();

        saida.append("Lista de terminais:\n");
        saida.append(listaTerminais.toString());
        saida.append("\n");
        saida.append("Lista de roteadores:\n");
        saida.append(listaRoteadores.toString());

        return saida.toString();
    }

    public Roteador buscarRoteador(String nome) {
        if (nome.isEmpty()) {
            throw new NullPointerException("O nome não pode ser vazio");
        }
        
        for (int i = 0; i < listaRoteadores.size(); i++) {
            Roteador temp = listaRoteadores.get(i);
            if(temp.getNome().equals(nome)){
                return temp;
            }
        }
        throw new RuntimeException("Roteador não encontrado!");

    }
    
    public Terminal buscarTerminal(String nome) {
        if (nome.isEmpty()) {
            throw new NullPointerException("O nome não pode ser vazio");
        }
        
        for (int i = 0; i < listaTerminais.size(); i++) {
            Terminal temp = listaTerminais.get(i);
            if(temp.getNome().equals(nome)){
                return temp;
            }
        }
        throw new RuntimeException("Terminal não encontrado!");

    }
}
