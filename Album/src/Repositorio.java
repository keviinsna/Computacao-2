import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

public class Repositorio{

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private final List<Colecionavel> todosItens;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas) {
        todosItens = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            Figurinha fig = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosItens.add(fig);
        }
    }

    public int getTotalItens() {
        return this.todosItens.size();
    }

    public Colecionavel getItem(int posicao){
        return this.todosItens.get(posicao);
    }
}
