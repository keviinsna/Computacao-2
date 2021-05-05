import java.util.HashMap;
import java.util.Map;

public class Turma {
    private Professor professor;
    private Disciplina disciplina;
    private Periodo periodo;

    private Map<Aluno, InfoAluno> infoAlunos = new HashMap();

    public Turma(Disciplina disciplina, Periodo periodo, Professor professor){
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.professor = professor;
    }

    /**
     * Inscreve aluno numa turma
     * @param aluno aluno
     */
    public void inscreverAluno(Aluno aluno){
        InfoAluno infoAlunoASerInserido = infoAlunos.get(aluno);
        if(infoAlunoASerInserido == null){
            infoAlunoASerInserido = new InfoAluno(aluno);
            infoAlunos.put(aluno, infoAlunoASerInserido);
        }
    }

    /**
     *
     * @param dre dre
     * @return retorna o nome do aluno caso ele esteja cadastrado na turma, caso contrário
     *          retorna nulo.
     */
    public String obterInfoAluno(long dre){
        for(Aluno aluno : this.infoAlunos.keySet()){
            if(aluno.getDre() == dre)
                return aluno.toString();
        }
        return null;
    }

    /**
     * Atribui uma nota à determinado aluno de uma turma.
     * @param dre dre
     * @param nota nota
     */
    public void atribuirMediaFinal(long dre, float nota){
        if (nota < 0 || nota > 10) return;
        for (Aluno aluno : infoAlunos.keySet()) {
            if(aluno.getDre() == dre){
                InfoAluno infoAlunoBuscado = infoAlunos.get(aluno);
                infoAlunoBuscado.mediaFinal = nota;
                infoAlunos.put(aluno, infoAlunoBuscado);
                break;
            }
        }
    }

    /**
     *
     * @param dre dre
     * @return a média final do aluno, caso ele ele esteja inscrito na turma
     *         -1 caso o aluno não esteja inscrito na turma
     */
    public float obterMediaFinal(long dre){
        for(Map.Entry<Aluno, InfoAluno> alunoInfoAluno: infoAlunos.entrySet()){
            Aluno aluno = alunoInfoAluno.getKey();
            InfoAluno infoAluno = alunoInfoAluno.getValue();

            if(aluno.getDre() == dre){
                return infoAluno.mediaFinal;
            }
        }
        return -1;
    }

    /**
     *
     * @return retorna uma string contendo todas as informações dos alunos
     */
    public String listarAlunos(){
        StringBuilder listaAlunos = new StringBuilder();
        for (InfoAluno infoAluno : this.infoAlunos.values()){
            listaAlunos.append(infoAluno.aluno.getNome());
            listaAlunos.append(" (DRE: ").append(infoAluno.aluno.getDre()).append(")\n");
        }
        return listaAlunos.toString();
    }

    private class InfoAluno{
        Aluno aluno;
        float mediaFinal;

        InfoAluno(Aluno aluno){
            this.aluno = aluno;
        }
    }
}
