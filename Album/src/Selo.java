import java.awt.*;

public class Selo implements Colecionavel{

    private float valorNominal;
    private String pais;

    private final int posicao;
    private final Image imagem;

    public Selo(int posicao, String urlDaImagem, float valorNominal) {
        this.posicao = posicao;
        this.imagem = obterImagem(urlDaImagem);
        this.valorNominal = valorNominal;
    }

    public float getValorNominal(){
        return this.valorNominal;
    }

    public String getPais(){
        return this.pais;
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    public Image getImagem(){
        return this.imagem;
    }

    public int getPosicao(){
        return this.posicao;
    }
}