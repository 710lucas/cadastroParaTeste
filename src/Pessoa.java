package src;

public class Pessoa {

    private String nome;
    private Cpf cpf;

    public Pessoa(String nome, String cpf) throws Exception {
        this.cpf = new Cpf(cpf);
        this.nome = nome;
    }

    public Pessoa(String nome, Cpf cpf){
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf) throws Exception {
        this.cpf = new Cpf(cpf);
    }

    public void setCpf(Cpf cpf){
        this.cpf = cpf;
    }

    public Cpf getCpf(){
        return cpf;
    }

    @Override
    public String toString(){
        return getNome()+" "+getCpf().getNumero();
    }

}
