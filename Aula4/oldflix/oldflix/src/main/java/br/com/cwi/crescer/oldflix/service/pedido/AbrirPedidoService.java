package br.com.cwi.crescer.oldflix.service.pedido;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.dto.pedido.PedidoDto;
import br.com.cwi.crescer.oldflix.enums.SituacaoPedido;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoPendenteComMesmoCpfException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
//import br.com.cwi.crescer.oldflix.service.locacao.SalvarLocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class AbrirPedidoService {

    @Autowired
    IPedidoRepository repository;


    public void salvar(Cliente cliente){
        if(Objects.isNull(cliente)){
            throw new ClienteNaoCadastradoException();
        }

        Long pedidosPendentes = cliente.getPedidos()
                .stream()
                .filter(p -> p.getSituacaoPedido().equals(SituacaoPedido.EM_USO)
                        || p.getSituacaoPedido().equals(SituacaoPedido.ATIVO))
                .count();

        if(pedidosPendentes > 0){
            throw new PedidoPendenteComMesmoCpfException();
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setSituacaoPedido(SituacaoPedido.EM_USO);
        pedido.setValorTotal(new BigDecimal(0));
        pedido.setValorDivida(new BigDecimal(0));
        repository.save(pedido);
    }
}
