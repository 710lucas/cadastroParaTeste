package src;

public class Cpf {

    //formato: 000.000.000-00
    private String numero;

    public Cpf(String numero) throws Exception {

        validaNumero(numero);

        this.numero = numero;
    }

    public void setNumero(String numero) throws Exception {
        validaNumero(numero);
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }



    public static boolean validaDigitos(String numero){
        for(int i = 0; i<numero.length(); i++){
            if(i!=3 && i!=7 && i!=11){
                try{
                    int tmp = Integer.parseInt(String.valueOf(numero.charAt(i)));
                } catch (NumberFormatException n){
                    return false;
                }
            }
        }

        return true;
    }

    public static void validaNumero(String numero) throws Exception {

        if((numero.charAt(3) != '.' && numero.charAt(7) != '.' && numero.charAt(11) != '-') || numero.length() != 14){
            throw new Exception("Formato invalido, informe um cpf no formato 000.000.000-00");
        }

        else if(!validaDigitos(numero)){
            throw new Exception("Digitos invalidos, algum caractere digitado não é um digito de 0-9");
        }

    }
}
