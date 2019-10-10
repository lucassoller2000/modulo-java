package br.com.cwi.crescer.oldflix.exception.fita;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FitaNaoCadastradaException extends RuntimeException {

    public FitaNaoCadastradaException(){
        super("Fita não cadastrada");
    }
}
