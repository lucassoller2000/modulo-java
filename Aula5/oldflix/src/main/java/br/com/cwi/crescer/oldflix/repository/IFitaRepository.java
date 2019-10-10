package br.com.cwi.crescer.oldflix.repository;

import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IFitaRepository extends Repository<Fita, Long> {
    List<Fita> findAll();

    Optional<Fita> findById(Long id);

    Optional<Fita> findBySituacaoFita(SituacaoFita situacaoFita);

    List<Fita> findByTitulo(String titulo);

    void save(Fita fita);

    void delete(Fita fita);
}
