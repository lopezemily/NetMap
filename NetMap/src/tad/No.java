package tad;

public class No<Tipo> {

    No<Tipo> prox;
    Tipo dado;

    public No(Tipo dado) {
        this.prox = null;
        this.dado = dado;
    }

    @Override
    public String toString() {
        return dado.toString();
    }
}
