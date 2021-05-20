import java.util.Random;

public class Pacotinho{

    private final Repositorio repo;
    private final Colecionavel[] pacotinho;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repo = repo;
        this.pacotinho = new Colecionavel[posicoesDesejadas.length];

        for(int i = 0; i < posicoesDesejadas.length; i++){
            pacotinho[i] = repo.getItem(posicoesDesejadas[i]);
        }
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
        this.repo = repo;
        this.pacotinho = new Colecionavel[quantFigurinhas];

        Random random = new Random();
        int totalItens = repo.getTotalItens();

        for(int i = 0; i < quantFigurinhas; i++){
            pacotinho[i] = repo.getItem(random.nextInt(totalItens) + 1);
        }
    }

    public Colecionavel[] getFigurinhas() {
        return this.pacotinho;
    }
}