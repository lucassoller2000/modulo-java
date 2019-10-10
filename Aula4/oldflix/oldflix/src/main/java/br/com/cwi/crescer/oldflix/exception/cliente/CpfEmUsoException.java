package br.com.cwi.crescer.oldflix.exception.cliente;

public class CpfEmUsoException extends RuntimeException{

    public CpfEmUsoException(){
        super("Este CPF já está em uso");
    }
}
