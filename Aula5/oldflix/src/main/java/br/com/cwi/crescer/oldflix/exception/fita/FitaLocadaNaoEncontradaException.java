package br.com.cwi.crescer.oldflix.exception.fita;

public class FitaLocadaNaoEncontradaException extends RuntimeException {
    public FitaLocadaNaoEncontradaException(){
        super("Nenhum fita locada com esse título foi encontrada");
    }
}
