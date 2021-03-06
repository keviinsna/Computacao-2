import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album <T extends Colecionavel> {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio<T> repositorio;
    private final int quantItensPorPacotinho;

    private final List<T> itensColados;  // direct addressing
    private int quantItensColados;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private final Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio<T> repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.itensColados = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.itensColados.add(null);
        }
        this.quantItensColados = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho<T> pacotinho) {
        T[] itensDoPacotinho = pacotinho.getItens();
        if (itensDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (T fig : pacotinho.getItens()) {
            final int posicao = fig.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito pior
//                Integer contRepetidas = this.contRepetidasByPosicao.get(posicao);
//                this.contRepetidasByPosicao.put(
//                        posicao, contRepetidas == null ? 1 : contRepetidas + 1);

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.itensColados.set(posicao, fig);
                this.quantItensColados++;
            }
        }
    }

    public T getItemColado(int posicao) {
        return possuiItemColado(posicao)? this.itensColados.get(posicao) : null;
    }

    public boolean possuiItemColado(int posicao) {
        if(posicao <= 0 || posicao > getTamanho()) return false;
        return this.itensColados.get(posicao) != null;
    }

    public boolean possuiItemRepetido(int posicao) {
        return this.contRepetidasByPosicao.containsKey(posicao);
    }

    public int getTamanho() {
        return this.repositorio.getTotalItens();
    }

    public int getQuantItensColados() {
//        int contador = 0;
//        for (Figurinha fig : this.figurinhasColadas) {
//            if (fig != null) {
//                contador++;
//            }
//        }
//        return contador;

        // melhor jeito: atributo!
        return this.quantItensColados;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        int minimoItensColadasParaAutoCompletar = (int) (this.getTamanho() * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);
        if(minimoItensColadasParaAutoCompletar <= this.quantItensColados){
            int[] posicoesDesejadas = new int[]{1, 1, 1};
            int index = 0;
            for(int i = 1; i < this.itensColados.size(); i++){
                T fig = this.itensColados.get(i);
                if (fig == null){
                    posicoesDesejadas[index++] = i;
                    if(index == this.quantItensPorPacotinho){
                        index = 0;
                        receberNovoPacotinho(new Pacotinho<T>(this.repositorio, posicoesDesejadas));
                        posicoesDesejadas[0] = posicoesDesejadas[1] = posicoesDesejadas[2] = 1;
                    }
                }
            }
            if(index != 0)
                receberNovoPacotinho(new Pacotinho<T>(this.repositorio, posicoesDesejadas));
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }
}