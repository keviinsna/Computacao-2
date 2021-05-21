import java.util.ArrayList;
import java.util.List;

public class Repositorio <T extends Colecionavel>{

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private final List<T> todosItens;

    @SuppressWarnings("unchecked")
    public Repositorio(String sufixoUrlImagens, int quantItens, String tipo) {
        todosItens = new ArrayList<>(quantItens);
        for (int i = 1; i <= quantItens; i++) {
            T fig = (T) ColecionavelFactory.create(tipo, i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosItens.add(fig);
        }
    }

    public int getTotalItens() {
        return this.todosItens.size();
    }

    public T getItem(int posicao){
        for(T itemColecionvel: this.todosItens){
            if(itemColecionvel.getPosicao() == posicao)
                return itemColecionvel;
        }
        return null;
    }
}