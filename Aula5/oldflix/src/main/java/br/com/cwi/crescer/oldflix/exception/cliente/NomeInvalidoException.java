package br.com.cwi.crescer.oldflix.exception.cliente;

public class NomeInvalidoException extends RuntimeException{
    public NomeInvalidoException(){
        super("Informe um nome válido");
    }
}
