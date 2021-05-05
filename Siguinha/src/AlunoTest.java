import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class AlunoTest {

    private static final float MAX_FLOAT_DIFF = 0.000001f;

    private Aluno fulana;
    private Disciplina algGraf;
    private Disciplina calculo1;
    private Periodo periodo20201;
    private Periodo periodo20202;

    private Siguinha siguinha;
    private Aluno ciclana, beltrana, kevin;
    private Professor jose, maria;
    private Turma turmaCalculo1, turmaAlgGraf;

    private DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
    private char decimalSeparator = decimalFormat.getDecimalFormatSymbols().getDecimalSeparator();

    @Before
    public void setUp() {
        fulana = new Aluno(1234, "Fulana de Tal");

        algGraf = new Disciplina("Algoritmos e Grafos", 4, "MAB704");
        calculo1 = new Disciplina("Calculo 1", 4, "MAB704");
        periodo20201 = new Periodo(2020, 1);
        periodo20202 = new Periodo(2020, 2);

        siguinha = new Siguinha();

        ciclana = new Aluno(2345, "Ciclana");
        beltrana = new Aluno(5877, "Beltrana");
        kevin = new Aluno(1220, "Kevin");

        jose = new Professor("José", 1998);
        maria = new Professor("Maria", 2000);

        turmaCalculo1 = new Turma(calculo1, periodo20201, maria);
        turmaAlgGraf = new Turma(algGraf, periodo20202, jose);

    }

    @Test
    public void testarAtualizacaoCraComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                10, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos das disciplinas em que houve aprovação",
                4, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Algoritmos e Grafos - 10" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarAtualizacaoCraComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                3, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos APENAS das disciplinas em que houve aprovação",
                0, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasNoMesmoPeriodo() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 4, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20201);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasEmPeriodosDistintos() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20202);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0\n" +
                "2020.2 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());

    }

    @Test
    public void testarInsersacoDeUmNumeroMuitoGrandeDeDisciplinas() {
        for (int i = 0; i < 10_000; i++) {
            Disciplina disciplina = new Disciplina("blah" + i, 4, "MAB" + i);
            fulana.inserirItemHistorico(disciplina, 6, periodo20201);
        }
    }






    @Test
    public void cadastrarAlunoNoSiga(){
        siguinha.cadastrarAluno(6543, "Maria");
        assertEquals("O aluno cadastrado neste dre deve ser Maria", new Aluno (6543, "Maria"), siguinha.obterAluno(6543));
    }

    @Test
    public void obterAlunoNãoCadastrado(){
        assertEquals("Não pode haver aluno cadastrado", null, siguinha.obterAluno(1234));
    }

    @Test
    public void inserirAlunoNaTurma(){
        turmaCalculo1.inscreverAluno(kevin);
        turmaCalculo1.inscreverAluno(fulana);
        assertEquals("O aluno deve ser cadastrado", kevin.toString(), turmaCalculo1.obterInfoAluno(kevin.getDre()));
        assertEquals("O aluno deve ser cadastrado", fulana.toString(), turmaCalculo1.obterInfoAluno(fulana.getDre()));
    }

    @Test
    public void atribuirMediaFinal(){
        turmaCalculo1.inscreverAluno(kevin);
        turmaCalculo1.atribuirMediaFinal(kevin.getDre(), 9.8f);
        assertEquals("O aluno deve ser cadastrado na turma para que a atribuição de nota seja feita",
                9.8f, turmaCalculo1.obterMediaFinal(kevin.getDre()), MAX_FLOAT_DIFF);
    }

    @Test
    public void atribuirMediaFinalInexistente(){
        turmaCalculo1.inscreverAluno(kevin);
        turmaCalculo1.atribuirMediaFinal(kevin.getDre(), 12.9f);
        assertEquals("O aluno deve ser cadastrado na turma para que a atribuição de nota seja feita", 0,
                turmaCalculo1.obterMediaFinal(kevin.getDre()), MAX_FLOAT_DIFF);
    }

    @Test
    public void atribuirMediaFinalDeAlunoNaoCadastrado(){
        turmaCalculo1.atribuirMediaFinal(kevin.getDre(), 7.0f);
        assertEquals("O aluno deve ser cadastrado na turma para que a atribuição de nota seja feita", -1,
                turmaCalculo1.obterMediaFinal(kevin.getDre()), MAX_FLOAT_DIFF);
    }

    @Test
    public void obterMediaFinalDeAlunoInexistente(){
        turmaAlgGraf.atribuirMediaFinal(fulana.getDre(), 7.0f);
        assertEquals("O aluno deve possuir média final", -1, turmaAlgGraf.obterMediaFinal(beltrana.getDre()),  MAX_FLOAT_DIFF);
    }

    @Test
    public void listarAlunos(){
        turmaAlgGraf.inscreverAluno(kevin);
        turmaAlgGraf.atribuirMediaFinal(kevin.getDre(), 10.0f);
        turmaAlgGraf.inscreverAluno(fulana);
        turmaAlgGraf.atribuirMediaFinal(fulana.getDre(), 5.5f);
        turmaAlgGraf.inscreverAluno(ciclana);
        turmaAlgGraf.atribuirMediaFinal(ciclana.getDre(), 8.6f);
        turmaAlgGraf.inscreverAluno(beltrana);
        turmaAlgGraf.atribuirMediaFinal(beltrana.getDre(), 4.0f);

        String listaAlunosDesejada = "Kevin (DRE: " + kevin.getDre() + ")\n" +
                "Fulana de Tal (DRE: " + fulana.getDre() + ")\n" +
                "Ciclana (DRE: " + ciclana.getDre() + ")\n" +
                "Beltrana (DRE: " + beltrana.getDre() + ")\n";

        assertEquals("Lista de alunos", listaAlunosDesejada, turmaAlgGraf.listarAlunos());

    }

}