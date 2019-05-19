package modelos;

public class Terminal {

    private String nome;
    private String localizacao;
    public Roteador roteador;

    public Terminal(String nome, String localizacao) {
        setNome(nome);
        setLocalizacao(localizacao);
        setRoteador(null);
    }

    public Terminal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isEmpty()) {
            throw new NullPointerException("O nome do terminal não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        if (localizacao.isEmpty()) {
            throw new NullPointerException("O nome da localizacao não pode ser vazio");
        }
        this.localizacao = localizacao;
    }

    public Roteador getRoteador() {
        return roteador;
    }

    public void setRoteador(Roteador roteador) {
        this.roteador = roteador;
    }
    
    @Override
    public String toString() {
        return nome + " " + localizacao;
    }

}
