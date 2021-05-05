public class Pessoa {
    protected String nome;
    protected int anoNascimento;

    public final static int TAMANHO_MAXIMO_DO_NOME = 30;

    public Pessoa(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_DO_NOME) {
            // ToDo: lançar uma exceção!!!
            return;
        }

        this.nome = nome;
    }

    public int getAnoNascimento(){
        return this.anoNascimento;
    }

    public int getIdade(){
        return Siguinha.obterAnoCorrente() - this.anoNascimento;
    }

}
