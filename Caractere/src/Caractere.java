import java.util.HashSet;

public class Caractere {
    public static void main(String[] args) {
        encontrarCaracterMaisFrequente("arara");
    }
    public static void encontrarCaracterMaisFrequente(String string){
        int cont, contMaisFrequente = 0;
        char cMaisFrequente = 0;
        HashSet<Character> conjunto = new HashSet<>();

        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            cont = 0;
            if (!conjunto.contains(c)){
                conjunto.add(c);
                for (int j = 0; j < string.length(); j++){
                    if (c == string.charAt(j)) cont++;
                }
                if(cont > contMaisFrequente){
                    contMaisFrequente = cont;
                    cMaisFrequente = c;
                }
            }
        }
        System.out.printf("%c, %d", cMaisFrequente, contMaisFrequente);
    }
}
