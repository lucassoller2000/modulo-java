package br.com.cwi.crescer.oldflix.service.cliente;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarClientePorIdService {

    @Autowired
    private IClienteRepository repository;

    public Cliente buscar(Long id){

        if(Objects.isNull(id)){
            throw new ClienteNaoCadastradoException();
        }
        return repository
                .findById(id)
                .orElseThrow(()-> new ClienteNaoCadastradoException());
    }
}
