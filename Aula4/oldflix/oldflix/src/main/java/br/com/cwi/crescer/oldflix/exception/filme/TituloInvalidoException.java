package br.com.cwi.crescer.oldflix.exception.filme;

public class TituloInvalidoException extends RuntimeException{
    public TituloInvalidoException(){
        super("Informe um título válido");
    }
}
