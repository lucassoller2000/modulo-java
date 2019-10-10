package br.com.cwi.crescer.oldflix.exception.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoCadastradoException extends RuntimeException {

    public ClienteNaoCadastradoException(){
        super("Cliente não cadastrado");
    }
}
