import java.util.Random;

public class Pacotinho < T extends Colecionavel>{

    private final Repositorio<T> repo;
    private final T[] pacotinho;

    public Pacotinho(Repositorio<T> repo, int[] posicoesDesejadas) {
        this.repo = repo;
        this.pacotinho = (T[]) new Colecionavel[posicoesDesejadas.length];

        for(int i = 0; i < posicoesDesejadas.length; i++){
            pacotinho[i] = (T) repo.getItem(posicoesDesejadas[i]);
        }
    }

    /**
     * Produz um pacotinho com quantItens sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantItens a quantidade de figurinhas a constar no pacotinho
     */

    public Pacotinho(Repositorio<T> repo, int quantItens) {
        this.repo = repo;
        this.pacotinho = (T[]) new Colecionavel[quantItens];

        Random random = new Random();
        int totalItens = repo.getTotalItens();

        for(int i = 0; i < quantItens; i++){
            pacotinho[i] = repo.getItem(random.nextInt(totalItens) + 1);
        }
    }

    public T[] getItens() {
        return this.pacotinho;
    }
}