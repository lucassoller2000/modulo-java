package br.com.cwi.crescer.oldflix.exception.locacao;

public class LocacaoNaoCadastradaException extends RuntimeException{

    public LocacaoNaoCadastradaException(){
        super("Locação não cadastrada");
    }
}
