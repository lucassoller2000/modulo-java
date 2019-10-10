package br.com.cwi.crescer.oldflix.service.cliente;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfEmUsoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.exception.cliente.NomeInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SalvarClienteService {
    @Autowired
    private IClienteRepository repository;

    public void salvar(Cliente cliente){

        if(Objects.isNull(cliente)){
            throw new ClienteNaoCadastradoException();
        }

        if(Objects.isNull(cliente.getCpf()) || cliente.getCpf().isEmpty()){
            throw new CpfInvalidoException();
        }
        if(Objects.isNull(cliente.getNome()) || cliente.getNome().isEmpty()){
            throw new NomeInvalidoException();
        }

        if(repository.findByCpf(cliente.getCpf()).isPresent()){
            throw new CpfEmUsoException();
        }
        repository.save(cliente);
    }
}
