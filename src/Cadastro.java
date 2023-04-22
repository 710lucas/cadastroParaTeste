package src;

import java.util.ArrayList;
import java.util.Collections;

public class Cadastro {

    private ArrayList<String> colecao = new ArrayList<>();

    /*
    lista.get(1);
    lista.size();
    lista.add();
    lista.indexOf("aldemir");
     */


    public int getNumeroDeItens(){
        return colecao.size();
    }

    public boolean adiciona(String texto){
        if(texto == null)
            return false;

        colecao.add(texto);
        return true;
    }

    public boolean remove(String texto){
        if(!colecao.contains(texto))
            return false;

        colecao.remove(texto);
        return true;
    }
    public boolean remove(int posicao){
        if(posicao>colecao.size() || posicao<0)
            return false;

        colecao.remove(posicao);
        return true;
    }

    public int recuperaIndice(String texto){
        return colecao.indexOf(texto);
    }

    public boolean contem(String texto){
        return colecao.contains(texto) ? true : false;
    }

    public String recupera(int posicao){
        return colecao.get(posicao);
    }

    public void ordena(){
        Collections.sort(colecao);
    }

    @Override
    public String toString(){

        String stringFinal = "";
        for(String str : colecao){
            stringFinal+=str+"\n";
        }
        return stringFinal;
    }


}
