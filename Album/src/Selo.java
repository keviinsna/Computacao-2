import java.awt.*;

public class Selo implements Colecionavel{

    private float valorNominal;
    private String pais;

    private final int posicao;
    private final Image imagem;

    public Selo(int posicao, Image imagem) {
        this.posicao = posicao;
        this.imagem = imagem;
    }

    public float getValorNominal(){
        return this.valorNominal;
    }

    public String getPais(){
        return this.pais;
    }

    public Image getImagem(){
        return this.imagem;
    }
    public int getPosicao(){
        return this.posicao;
    }
}
