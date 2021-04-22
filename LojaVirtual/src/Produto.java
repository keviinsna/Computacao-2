public class Produto {

    private String descricao;

    private String urlDaImagem;

    private float preco;

    private int quantidadeProduto;

    public Produto(String descricao, String urlDaImagem) {
        this.descricao = descricao;
        this.urlDaImagem = urlDaImagem;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;
    }

    public int getPesoEmGramas() {
        return 0;  // ToDo IMPLEMENT ME!!!
    }

    public Dimensoes getDimensoes() {
        return null;  // ToDo IMPLEMENT ME!!!
    }

    public float precoEmReais() {
        return this.preco;
    }

    public void setPrecoEmReais(float preco) {
        this.preco = preco;
    }

    public int getQuantidadeProduto(){
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto){
        this.quantidadeProduto += quantidadeProduto;
    }

    public String getUrlDaImagem() {
        return urlDaImagem;  // ToDo IMPLEMENT ME!!!
    }

}
