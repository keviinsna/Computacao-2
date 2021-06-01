import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite <T>{

    private Map<String, Usuario> usuarioByEmail;
    private Map<String, Integer> quantByHastag;
    private String hashtagMaisComum;
    private int quantHashtagMaisComum;

    public static int TAMANHO_MAXIMO_TUITES = 120;

    public TuiterLite(){
        usuarioByEmail = new HashMap<>();
        quantByHastag = new HashMap<>();

        hashtagMaisComum = null;
        quantHashtagMaisComum = 0;
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) throws UsuarioJaExisteException {
        Usuario usuario = this.usuarioByEmail.get(email);

        if(usuario != null){
            throw new UsuarioJaExisteException();
        }

        usuario = new Usuario(nome, email);
        this.usuarioByEmail.put(email, usuario);
        return usuario;
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite<T> tuitarAlgo(Usuario usuario, String texto) throws TamanhoMaximoExcedidoException,
                                                                  UsuarioDesconhecidoException{

        if( texto == null || texto.length() == 0 || usuario == null){
            return null;
        }

        if(texto.length() > TAMANHO_MAXIMO_TUITES){
            throw new TamanhoMaximoExcedidoException();
        }

        if(!this.usuarioByEmail.containsKey(usuario.getEmail())){
            throw new UsuarioDesconhecidoException();
        }

        Set<String> hashtags = obterHashtags(texto);

        for (String hashtag : hashtags){
            int quantidadeHashtag = this.quantByHastag.getOrDefault(hashtag, 0);
            int novaQuantidadeHashtag = quantidadeHashtag + 1;
            this.quantByHastag.put(hashtag, novaQuantidadeHashtag);

            if(this.quantHashtagMaisComum < novaQuantidadeHashtag){
                this.quantHashtagMaisComum = novaQuantidadeHashtag;
                this.hashtagMaisComum = hashtag;
            }
        }
        return new Tuite<T>(usuario, texto, hashtags);
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return this.hashtagMaisComum;
    }

    public Set<String> obterHashtags(String texto){

        Set<String> hashtags = null;

        String[] palavrasDaFrase = texto.split("([^(a-z|A-Z|0-9|ã-ü|#)])+");

        for(String palavra : palavrasDaFrase){
            palavra = palavra.replaceAll("#+", "#");

            if(palavra.charAt(0) == '#'){

                String[] tags = palavra.split("#"); // Caso a palavra tenha várias #, mas não uma do lado da outra
                for(String tag: tags){
                    if (tag.length() > 0){
                        if(hashtags == null){
                            hashtags = new HashSet<>();
                        }
                        hashtags.add("#" + tag);
                    }
                }
            }
        }
        return hashtags == null ? Collections.emptySet() : hashtags;
    }

    public static void main(String[] args) {
        String tuite = "#####test#lab estou resolvendo. O exercicio ###lab7. Então, , , , , ,vamos ver!####";

        List<String> hashtags = new ArrayList<>();
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < tuite.length(); i++) {
//            final char c = tuite.charAt(i);
//            if (c == '#') {
//                if (sb.length() > 0) {
//                    if (sb.charAt(sb.length() - 1) == '#') {
//                        continue;  // ignorando a # corrente, já que o anterior tb era #
//                    }
//                    String hashtag = sb.toString();
//                    hashtags.add(hashtag);
//                    sb.setLength(1);  // vai manter apenas o primeiro caracter, que já é uma hashtag
//                } else {
//                    sb.append("#");
//                }
//            } else if (isAlphanumeric(c)) {
//                if (sb.length() > 0) {
//                    sb.append(c);
//                }
//
//            } else {  // não é alfanumérico e nem o símbolo #
//                if (sb.length() > 0) {
//                    String hashtag = sb.toString();
//                    hashtags.add(hashtag);
//                    sb.setLength(0);
//                }
//            }
//        }





        String[] palavrasDaFrase = tuite.split("([^(a-z|A-Z|0-9|ã-ü|#)])+");   // "escapando" a contrabarra para que minha string seja "CONTRABARRA S"
        System.out.println(tuite);

        for (String palavra : palavrasDaFrase) {

            if (palavra.charAt(0) == '#') {
                palavra = palavra.replaceAll("#+", "#");

                String[] tags = palavra.split("#");
                for (String tag : tags) {
                    if (tag.length() > 0) {
                        hashtags.add("#" + tag);
                    }
                }
            }
        }

        for (String hashtag : hashtags) {
            System.out.println(hashtag);
        }



//
//        String numerosSeparadosPorVirgula = "13+54++++7000+4,78,,,,98989+8";
//        String[] numeros = numerosSeparadosPorVirgula.split("(\\++)|,+");   // "escapando" a contrabarra para que minha string seja "CONTRABARRA S"
//        System.out.println(numerosSeparadosPorVirgula);
//        for (String numeroComoString : numeros) {
////            int x = Integer.parseInt(numeroComoString);
//            System.out.println(numeroComoString);
//        }
//
//        String regex1 = ".*[A-Z]+.*";
//        String regex2 = ".*[a-z]+.*";
//        String regex3 = ".*[0-9]+.*";
//        String palavra = "VyuyH87aa";
//
//
//        if (palavra.matches(regex1) && palavra.matches(regex2) && palavra.matches(regex3)) {
//            System.out.println("Ok! Casou com a regexp");
//        } else {
//            System.out.println("Não casou com a regexp!!!");
//        }
//


    }
}
