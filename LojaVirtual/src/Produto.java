public class Produto {

    private String descricao;
    private String urlDaImagem;

    public Produto(String descricao, String urlDaImagem) {
        this.descricao = descricao;
        this.urlDaImagem = urlDaImagem;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;  // ToDo IMPLEMENT ME!!!
    }

    public int getPesoEmGramas() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public Dimensoes getDimensoes() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public float precoEmReais() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public void setPrecoEmReais(float preco) {
        // ToDo IMPLEMENT ME!!!
    }

    public String getUrlDaImagem() {
        return null;  // ToDo IMPLEMENT ME!!!
    }
}
