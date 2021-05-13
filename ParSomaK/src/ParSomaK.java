import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParSomaK  {

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(Arrays.asList(1, 5, -10, 56, 44, 12, 18));
        encontrarPar(lista, 100);
    }

    public static void encontrarPar(List<Integer> lista, int k){
        Collections.sort(lista);

        for (int num : lista){

            if(Collections.binarySearch(lista, k-num) >= 0){
                System.out.printf("%d e %d\n", num, k-num);
                break;
            }
        }
    }
}
