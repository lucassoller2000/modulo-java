package br.com.cwi.crescer.oldflix.exception.filme;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmeNaoCadastradoException extends RuntimeException {

    public FilmeNaoCadastradoException(){
        super("Filme não cadastrado");
    }
}