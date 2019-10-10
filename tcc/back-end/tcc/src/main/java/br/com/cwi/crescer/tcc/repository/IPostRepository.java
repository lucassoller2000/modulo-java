package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.repository.projection.PostResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends Repository<Post, Long> {
    Optional<Post> findById(Long id);

    PostResponse queryById(Long id);

    @Query("select p from Post p where p.usuario.id = :id order by p.dataPostagem desc")
    List<PostResponse> queryByUsuario(@Param("id") Long id);

    @Query("select p from Post p where p.privacidadePost like 'PUBLICO' and p.usuario.id = :id order by p.dataPostagem desc")
    List<PostResponse> findByUsuario(@Param("id") Long id);

    @Query("select p from Post p inner join Usuario u on p.usuario.id = u.id inner join Amizade a on a.usuarioDestinatario.id = u.id where a.statusAmizade like 'AMIGOS' and p.privacidadePost like 'PUBLICO' and a.usuarioRemetente.id = :id order by p.dataPostagem desc")
    List<PostResponse> findAll(@Param("id") Long id);

    Optional<Post> save(Post post);

    void delete(Post post);
}
