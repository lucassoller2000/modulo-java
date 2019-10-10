package br.com.cwi.crescer.oldflix.exception.fita;

public class FitaNaoCadastradaOuIndisponivelException extends RuntimeException {
    public FitaNaoCadastradaOuIndisponivelException(){
        super("Fita não cadastrada ou indisponível");
    }
}
