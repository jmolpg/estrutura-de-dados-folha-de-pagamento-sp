import java.util.ArrayList;
import java.util.List;

public class ArvoreBinariaBusca {
    private static class No {
        private RegistroFolhaPagamento registro;
        private No esquerda;
        private No direita;

        public No(RegistroFolhaPagamento registro) {
            this.registro = registro;
        }
    }

    private No raiz;

    public void inserir(RegistroFolhaPagamento registro) {
        raiz = inserirRecursivo(raiz, registro);
    }

    private No inserirRecursivo(No no, RegistroFolhaPagamento registro) {
        if (no == null) {
            return new No(registro);
        }

        if (registro.compareTo(no.registro) < 0) {
            no.esquerda = inserirRecursivo(no.esquerda, registro);
        } else if (registro.compareTo(no.registro) > 0) {
            no.direita = inserirRecursivo(no.direita, registro);
        }

        return no;
    }

    public boolean remover(RegistroFolhaPagamento registro) {
        No[] no = new No[1];
        boolean encontrado = buscarRecursivo(raiz, registro, no);
        if (encontrado) {
            raiz = removerRecursivo(raiz, registro);
        }
        return encontrado;
    }

    private No removerRecursivo(No no, RegistroFolhaPagamento registro) {
        if (no == null) {
            return null;
        }

        if (registro.compareTo(no.registro) < 0) {
            no.esquerda = removerRecursivo(no.esquerda, registro);
        } else if (registro.compareTo(no.registro) > 0) {
            no.direita = removerRecursivo(no.direita, registro);
        } else {
            if (no.esquerda == null && no.direita == null) {
                return null;
            } else if (no.esquerda == null) {
                return no.direita;
            } else if (no.direita == null) {
                return no.esquerda;
            } else {
                No sucessor = encontrarMenorNo(no.direita);
                no.registro = sucessor.registro;
                no.direita = removerRecursivo(no.direita, sucessor.registro);
            }
        }

        return no;
    }

    private No encontrarMenorNo(No no) {
        No atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    public List<RegistroFolhaPagamento> listarEmOrdem() {
        List<RegistroFolhaPagamento> registros = new ArrayList<>();
        listarEmOrdemRecursivo(raiz, registros);
        return registros;
    }

    private void listarEmOrdemRecursivo(No no, List<RegistroFolhaPagamento> registros) {
        if (no != null) {
            listarEmOrdemRecursivo(no.esquerda, registros);
            registros.add(no.registro);
            listarEmOrdemRecursivo(no.direita, registros);
        }
    }

    private boolean buscarRecursivo(No no, RegistroFolhaPagamento registro, No[] referenciaNo) {
        if (no == null) {
            return false;
        }

        if (registro.compareTo(no.registro) == 0) {
            referenciaNo[0] = no;
            return true;
        }

        if (registro.compareTo(no.registro) < 0) {
            return buscarRecursivo(no.esquerda, registro, referenciaNo);
        }

        return buscarRecursivo(no.direita, registro, referenciaNo);
    }

}
