import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Colecionavel> figurinhasColadas;  // direct addressing
    private int quantFigurinhasColadas;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.figurinhasColadas = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.figurinhasColadas.add(null);
        }
        this.quantFigurinhasColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Colecionavel[] figurinhasDoPacotinho = pacotinho.getFigurinhas();
        if (figurinhasDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (Colecionavel fig : pacotinho.getFigurinhas()) {
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
                if(posicao != 0){
                    this.figurinhasColadas.set(posicao, fig);
                    this.quantFigurinhasColadas++;
                }
            }
        }
    }

    public Colecionavel getItemColado(int posicao) {
        return possuiItemColado(posicao)? this.figurinhasColadas.get(posicao) : null;
    }

    public boolean possuiItemColado(int posicao) {
        if(posicao <= 0 || posicao > this.figurinhasColadas.size() - 1) return false;

        return this.figurinhasColadas.get(posicao) != null;
    }

    public boolean possuiItemRepetido(int posicao) {
        return this.contRepetidasByPosicao.containsKey(posicao);
    }

    public int getTamanho() {
        return this.repositorio.getTotalFigurinhas();
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
        return this.quantFigurinhasColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        int minimoFigurinhasColadasParaAutoCompletar = (int) (this.getTamanho() * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);
        if(minimoFigurinhasColadasParaAutoCompletar <= this.quantFigurinhasColadas){
            int[] posicoesDesejadas = new int[this.quantItensPorPacotinho];
            int index = 0;
            for(int i = 1; i < this.figurinhasColadas.size(); i++){
                Colecionavel fig = this.figurinhasColadas.get(i);
                if (fig == null){
                    posicoesDesejadas[index++] = i;
                    if(index == 3){
                        index = 0;
                        receberNovoPacotinho(new Pacotinho(this.repositorio, posicoesDesejadas));
                        posicoesDesejadas[0] = posicoesDesejadas[1] = posicoesDesejadas[2] = 0;
                    }
                }
            }
            if(posicoesDesejadas[0] != 0)
                receberNovoPacotinho(new Pacotinho(this.repositorio, posicoesDesejadas));
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }
}