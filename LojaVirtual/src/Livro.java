public class Livro extends Produto {

    private String editora;

    private String nome;
    public Livro(String nome, String editora) {
        super(nome, null);
        this.editora = editora;
        this.nome = nome;
    }

    private int numeroDePaginas() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }

    public String getTrechoEmDestaque() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public String getAutor() {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public int getAnoDePublicacao() {
        return 0;  // ToDo IMPLEMENT ME!!!!
    }
}
