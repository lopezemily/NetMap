package tad;

public class ListaDinamica<Tipo> {

    private No<Tipo> inicio;
    private No<Tipo> fim;
    private int total;

    public ListaDinamica() {
        inicio = fim = null;
        total = 0;
    }

    public void insert(Tipo dado) {
        insert(dado, size());
    }

    public void insert(Tipo dado, int posicao) {
        No<Tipo> novoNo = new No<Tipo>(dado);

        if (posicao < 0 || posicao > total) {
            throw new RuntimeException("Posicao Invalida!!");
        }

        if (posicao == 0) {
            if (isEmpty()) {
                inicio = fim = novoNo;
            } else {
                novoNo.prox = inicio;
                inicio = novoNo;
            }
        } else {
            if (posicao == size()) {
                fim.prox = novoNo;
                fim = novoNo;
            } else {
                No<Tipo> aux = inicio;
                int cont = 0;
                while (aux != null && !(cont == posicao - 1)) {
                    aux = aux.prox;
                    cont++;
                }
                novoNo.prox = aux.prox;
                aux.prox = novoNo;
            }
        }
        total++;
    }

    public Tipo remove(int posicao) {
        No<Tipo> temp;

        if (posicao < 0 || posicao >= total || isEmpty()) {
            throw new RuntimeException("Posicao Invalida ou Lista Vazia");
        }

        if (posicao == 0) {
            temp = inicio;
            if (size() == 1) {
                inicio = fim = null;
            } else {
                inicio = inicio.prox;
            }
        } else {
            No<Tipo> aux = inicio;
            int cont = 0;
            while (aux != null && !(cont == posicao - 1)) {
                aux = aux.prox;
                cont++;
            }
            temp = aux.prox;
            aux.prox = temp.prox;
        }
        total--;
        return temp.dado;
    }

    public void set(Tipo dado, int posicao) {
        if (posicao < 0 || posicao >= total || isEmpty()) {
            throw new RuntimeException("Posicao Invalida ou Lista Vazia");
        }

        No<Tipo> aux = inicio;
        int cont = 0;

        while (aux != null && posicao != cont) {
            aux = aux.prox;
            cont++;
        }
        aux.dado = dado;
    }

    public Tipo get(int posicao) {
        if (posicao < 0 || posicao >= total || isEmpty()) {
            throw new RuntimeException("Posicao Invalida ou Lista Vazia");
        }

        No<Tipo> aux = inicio;
        int cont = 0;

        while (aux != null && posicao != cont) {
            aux = aux.prox;
            cont++;
        }
        return aux.dado;
    }
    
    public int getPosicao(Tipo dado){
        if (isEmpty()) {
            return -1;
        }

        No<Tipo> aux = inicio;
        int cont = 0;
        while (aux != null) {
            if (aux.dado == dado) {
                return cont;
            }
            aux = aux.prox;
            cont ++;
        }
        return -1;
    }
    
    public void remove(Tipo dado){
        int posicao = getPosicao(dado);
        remove(posicao);
    }

    public boolean exist(Tipo dado) {
        if (isEmpty()) {
            return false;
        }

        No<Tipo> aux = inicio;
        while (aux != null) {
            if (aux.dado == dado) {
                return true;
            }
            aux = aux.prox;
        }
        return false;
    }

    public int size() {
        return total;
    }

    public boolean isEmpty() {
        return total == 0;
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();

        No atual = inicio;
        while (atual != null) {
            saida.append(atual.dado).append(" ");

            atual = atual.prox;
        }
        return saida.toString();
    }

}
