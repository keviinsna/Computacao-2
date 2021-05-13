import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {

    private Usuario fulano;
    private Usuario kevin;
    private Usuario ciclano;

    private List<Usuario> listaAmigos;

    private static final int QUANTAMIGOS = 200_000;

    private CalculadorIntersecao calculadorIntersecao;

    @Before
    public void setUp(){
        fulano = new Usuario(123, new CalculadorIntersecaoEsperto());
        ciclano = new Usuario(132, new CalculadorIntersecaoViaBuscaBinaria());
        kevin = new Usuario(321, new CalculadorIntersecaoIngenuo());

        listaAmigos = new ArrayList<>();
        for (int i = 0; i < QUANTAMIGOS; i++){
            listaAmigos.add(new Usuario(i, new CalculadorIntersecaoEsperto()));
        }
    }

    public void povoarAmigos(Usuario usuario, int quantAmigos, int multiplo){
        List<Usuario> listaAmigosDoUsuario = usuario.getAmigos();
        for (int i = 0; i < quantAmigos; i+=multiplo){
            listaAmigosDoUsuario.add(listaAmigos.get(i));
        }
    }

    @Test
    public void testarQuantidadeAmigosEmComum(){
        povoarAmigos(kevin, 50, 2);
        povoarAmigos(ciclano, 80, 3);

        assertEquals(9, kevin.obterQuantidadeDeAmigosEmComum(ciclano));
    }

    @Test
    public void testarPerformaceCalculadoras(){
        povoarAmigos(kevin, 200_000, 2);
        povoarAmigos(ciclano, 200_000,3);
        povoarAmigos(fulano, 200_000,3);

        long inicio = System.currentTimeMillis();
        assertEquals(33334, ciclano.obterQuantidadeDeAmigosEmComum(kevin));
        long fim = System.currentTimeMillis() - inicio;
        System.out.println("Tempo de execução via busca binária: " + fim/1000f + "s");

        inicio = System.currentTimeMillis();
        assertEquals(33334, fulano.obterQuantidadeDeAmigosEmComum(kevin));
        fim = System.currentTimeMillis() - inicio;
        System.out.println("Tempo de execução via busca esperta: " + fim/1000f + "s");

        inicio = System.currentTimeMillis();
        assertEquals(33334, kevin.obterQuantidadeDeAmigosEmComum(ciclano));
        fim = System.currentTimeMillis() - inicio;
        System.out.println("Tempo de execução via ingênuo: " + fim/1000f + "s");

    }
}
