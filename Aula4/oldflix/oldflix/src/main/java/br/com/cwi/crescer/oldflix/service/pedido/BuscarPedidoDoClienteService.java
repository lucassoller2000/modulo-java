package br.com.cwi.crescer.oldflix.service.pedido;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteSemPedidoException;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
import br.com.cwi.crescer.oldflix.service.cliente.BuscarClientePorCpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuscarPedidoDoClienteService {
    @Autowired
    IPedidoRepository repository;

    @Autowired
    BuscarClientePorCpfService buscarClientePorCpfService;

    public List<Pedido> buscar(String cpf){
        if(Objects.isNull(cpf)){
            throw new ClienteNaoCadastradoException();
        }
        Cliente cliente = buscarClientePorCpfService.buscarClientePorCpf(cpf);

        return repository.findAllByCliente(cliente);
    }
}
