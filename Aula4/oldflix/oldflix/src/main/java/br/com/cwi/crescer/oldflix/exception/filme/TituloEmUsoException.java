package br.com.cwi.crescer.oldflix.exception.filme;

public class TituloEmUsoException extends RuntimeException{
    public TituloEmUsoException(){
        super("Este título já está em uso");
    }
}
