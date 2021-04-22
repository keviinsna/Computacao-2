public class Usuario {

    private String nome, endereco;

    private long cpf;

    public Usuario(String nome, long cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public long getCpf(){
        return this.cpf;
    }

    @Override
    public boolean equals(Object o){

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return this.getCpf() == usuario.getCpf();

    }

}
