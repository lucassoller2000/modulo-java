package br.com.cwi.crescer.oldflix.exception.cliente;

public class CpfInvalidoException extends RuntimeException{

    public CpfInvalidoException(){
        super("Informe um CPF válido");
    }
}
