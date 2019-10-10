package br.com.cwi.crescer.oldflix.service.pedido;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class BuscarPedidoPorIdService {

    @Autowired
    IPedidoRepository repository;

    public Pedido buscar (Long id){
        if(Objects.isNull(id)){
            throw new PedidoNaoCadastradoException();
        }
        return repository
                .findById(id)
                .orElseThrow(()-> new PedidoNaoCadastradoException());
    }
}
