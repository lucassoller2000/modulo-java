package br.com.cwi.crescer.tcc.repository;
import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ICurtidaRepository extends Repository<Curtida, Long> {
    List<Curtida> findAll();

    Optional<Curtida> findById(Long id);

    Optional<Curtida> queryByUsuarioAndPost(Usuario usuario, Post post);

    List<Curtida> findByUsuario(Usuario usuario);

    List<Curtida> findByPost(Post post);

    Optional<Curtida> save(Curtida curtida);

    void delete(Curtida curtida);
}
