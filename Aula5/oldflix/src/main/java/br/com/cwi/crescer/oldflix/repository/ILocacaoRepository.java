package br.com.cwi.crescer.oldflix.repository;

import br.com.cwi.crescer.oldflix.dominio.Locacao;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ILocacaoRepository extends Repository<Locacao, Long> {
    List<Locacao> findAll();

    Optional<Locacao> findById(Long id);

    Optional<Locacao> findByPedido(Long id);

    Optional<Locacao> findByFita(Long id);

    List<Locacao> findAllByPedido(Pedido pedido);

    void save(Locacao locacao);

}
