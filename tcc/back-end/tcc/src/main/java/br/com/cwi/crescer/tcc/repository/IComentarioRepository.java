package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IComentarioRepository extends Repository<Comentario, Long> {
    List<Comentario> findAll();

    Optional<Comentario> findById(Long id);

    List<Comentario> findByUsuario(Usuario usuario);

    List<Comentario> findByPost(Post post);

    Optional<Comentario> save(Comentario comentario);

    void delete(Comentario comentario);
}
