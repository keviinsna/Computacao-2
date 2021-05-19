import java.util.List;
import java.util.Random;

public class Pacotinho{

    private Repositorio repo;
    private Colecionavel[] pacotinho;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repo = repo;
        this.pacotinho = new Figurinha[posicoesDesejadas.length];

        for(int i = 0; i < posicoesDesejadas.length; i++){
            pacotinho[i] = new Figurinha(posicoesDesejadas[i], null);
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
        int totalFigurinhas = repo.getTotalFigurinhas();

        for(int i = 0; i < quantFigurinhas; i++){
            pacotinho[i] = new Figurinha(random.nextInt(totalFigurinhas) + 1, null);
        }
    }

    public Colecionavel[] getFigurinhas() {
        return this.pacotinho;
    }
}
