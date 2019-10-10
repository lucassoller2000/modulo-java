package br.com.cwi.crescer.oldflix.service.cliente;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarClientePorCpfService {
    @Autowired
    private IClienteRepository repository;

    public Cliente buscarClientePorCpf(String cpf){

        if(Objects.isNull(cpf)){
            throw new CpfInvalidoException();
        }
        return repository
                .findByCpf(cpf)
                .orElseThrow(()-> new ClienteNaoCadastradoException());
    }
}
