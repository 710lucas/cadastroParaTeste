package src;

import java.util.ArrayList;
import java.util.Collections;

public class CadastroPessoas {

    private ArrayList<Pessoa> colecao = new ArrayList<>();

    /*
    lista.get(1);
    lista.size();
    lista.add();
    lista.indexOf("aldemir");
     */


    public int getNumeroDeItens(){
        return colecao.size();
    }

    public boolean adiciona(String texto, String cpf) throws Exception{
        if(texto == null || cpf == null)
            return false;

        colecao.add(new Pessoa(texto, cpf));
        return true;
    }
    public boolean adiciona(String texto, Cpf cpf){
        if(texto == null || cpf == null)
            return false;

        colecao.add(new Pessoa(texto, cpf));
        return true;
    }

    public boolean adiciona(Pessoa p) {
        if(p == null)
            return false;

        colecao.add(p);
        return true;
    }

    public boolean remove(String texto, String cpf) throws Exception {
        if(procuraPorCpf(cpf, colecao) == -1)
            return false;

        colecao.remove(procuraPorCpf(cpf, colecao));
        return true;
    }
    public boolean remove(String texto, Cpf cpf) {
        if(!colecao.contains(new Pessoa(texto, cpf)))
            return false;

        colecao.remove(new Pessoa(texto, cpf));
        return true;
    }
    public boolean remove(Pessoa pessoa){
        if(!colecao.contains(pessoa))
            return false;

        colecao.remove(pessoa);
        return true;
    }
    public boolean remove(int posicao){

        colecao.remove(posicao);
        return true;
    }

    public int recuperaIndice(String texto, String cpf){
        return procuraPorCpf(cpf, colecao);
    }
    public int recuperaIndice(String texto, Cpf cpf){
        return procuraPorCpf(cpf.getNumero(), colecao);
    }
    public int recuperaIndice(Pessoa pessoa){
        return colecao.indexOf(pessoa);
    }

    public boolean contem(String texto, String cpf) throws Exception{
        if(texto == null || cpf  == null)
            return false;
        return colecao.contains(colecao.get(procuraPorCpf(cpf, colecao)));
    }
    public boolean contem(String texto, Cpf cpf){
        return colecao.contains(new Pessoa(texto, cpf));
    }

    public Pessoa recupera(int posicao){
        return colecao.get(posicao);
    }

    public void ordena(){
        ArrayList<String> cpfs = new ArrayList<>();
        for (Pessoa pessoa : colecao) {
            cpfs.add(pessoa.getCpf().getNumero());
        }

        Collections.sort(cpfs);
        ArrayList<Pessoa> colecaoOrdenada = new ArrayList<>();
        for(String cpf : cpfs){
            colecaoOrdenada.add(colecao.get(procuraPorCpf(cpf, colecao)));
        }
        colecao = colecaoOrdenada;
    }

    @Override
    public String toString(){

        String stringFinal = "";
        for(Pessoa pessoa : colecao){
            stringFinal+=pessoa.getNome()+" "+pessoa.getCpf().getNumero()+"\n";
        }
        return stringFinal;
    }


    public static int procuraPorNome(String nome, ArrayList<Pessoa> colecao){
        for(Pessoa pessoa : colecao){
            if(pessoa.getNome().equals(nome))
                return colecao.indexOf(pessoa);
        }
        return -1;
    }
    public static int procuraPorCpf(String cpf, ArrayList<Pessoa> colecao){
        for(Pessoa pessoa : colecao){
            if(pessoa.getCpf().getNumero().equals(cpf)) {
                return colecao.indexOf(pessoa);
            }
        }
        return -1;
    }


}
