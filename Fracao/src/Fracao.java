/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */

    private int numerador;
    private int denominador;
    private boolean sinal;

    public Fracao(int numerador, int denominador) {

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }

        this.sinal = (numerador <= 0 && denominador <= 0) || (numerador >= 0 && denominador >= 0);
        this.numerador   = Math.abs(numerador);
        this.denominador = Math.abs(denominador);


    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {
        return this.sinal;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
        return this.numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() {
        return denominador;
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
        float valorNumerico;
        valorNumerico = (float) this.numerador / this.denominador;

        return (this.sinal)? valorNumerico : -valorNumerico;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {
        int mdc = AritmeticaUtils.mdc(numerador, denominador);

        if(mdc == 1) return this;

        return new Fracao(this.numerador / mdc, this.denominador / mdc);
    }

    @Override
    public String toString() {
        String fracaoString = "";

        if(this.numerador == 0) return "0";

        if(!sinal)
            fracaoString += '-';

        fracaoString += numerador;

        if(this.denominador != 1)
            fracaoString += "/" + this.denominador;

        return fracaoString;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        Fracao fracao = (Fracao) o ;

        Fracao fracaoGeratriz = fracao.getFracaoGeratriz();
        Fracao fracaoGeratriz2 = this.getFracaoGeratriz();

        return fracaoGeratriz.getNumerador() == fracaoGeratriz2.getNumerador() &&
                fracaoGeratriz.getDenominador() == fracaoGeratriz2.getDenominador();
    }

}
