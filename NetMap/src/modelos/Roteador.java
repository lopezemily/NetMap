package modelos;

import tad.ListaDinamica;

public class Roteador {

    private String nome;
    private String operadora;
    public ListaDinamica<Terminal> listaTerminais;
    public ListaDinamica<Roteador> listaRoteadores;

    public Roteador(String nome, String operadora) {
        setNome(nome);
        setOperadora(operadora);
        this.listaTerminais = new ListaDinamica<Terminal>();
        this.listaRoteadores = new ListaDinamica<Roteador>();
    }

    public void setNome(String nome) {
        if (nome.isEmpty()) {
            throw new NullPointerException("O nome do roteador não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setOperadora(String operadora) {
        if (operadora.isEmpty()) {
            throw new NullPointerException("O nome da operadora não pode ser vazio");
        }
        this.operadora = operadora;
    }

    public String getOperadora() {
        return operadora;
    }

}
