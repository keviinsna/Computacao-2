import java.util.ArrayList;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private ArrayList<Usuario> usuarios;

    private ArrayList<Produto> produtos;

    private static final Loja instanciaUnica = new Loja();

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    private Loja() {
        // escrevo código normalmente para o construtor
        usuarios = new ArrayList<>();
        produtos = new ArrayList<>();
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        if(quantidadeAIncluir >= 0){
            if(!this.produtos.contains(produto))
                this.produtos.add(produto);

            produto.setQuantidadeProduto(quantidadeAIncluir);
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        if(!usuarios.contains(usuario))
            this.usuarios.add(usuario);
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(
            Produto produto, int quantidadeDesejada, Usuario usuario) {
        if(!produtos.contains(produto) || !usuarios.contains(usuario) || quantidadeDesejada > produto.getQuantidadeProduto())
            return null;

        return new Recibo(produto, quantidadeDesejada, usuario);
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        return this.produtos.contains(produto)? produto.getQuantidadeProduto() : -1;
    }
}
