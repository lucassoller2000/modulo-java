package br.com.cwi.crescer.oldflix.exception.filme;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException(){
        super("Informe uma categoria válida");
    }
}
