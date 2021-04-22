public class Recibo {

    private Produto produto;

    private Usuario usuario;

    private int quantidadeDesejadaDoProduto;

    private float valorTotalDaCompra;

    public Recibo(Produto produto, int quantidadeDesejada, Usuario usuario){
        this.produto = produto;
        this.usuario = usuario;

        this.quantidadeDesejadaDoProduto = quantidadeDesejada;
        this.valorTotalDaCompra = produto.precoEmReais() * quantidadeDesejada;

        produto.setQuantidadeProduto(-quantidadeDesejadaDoProduto);
    }

    public float getValorTotalDaCompra() {
        return this.valorTotalDaCompra;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    public String toString(){
        String[] classe = this.produto.getClass().toString().split(" ");
        return String.format("Recibo no valor de R$%.2f para %s referente à compra de %d unidades de %s: %s",
                this.valorTotalDaCompra, this.usuario.getNome(), quantidadeDesejadaDoProduto,
                classe[1], this.produto.getDescricao());
    }
}
