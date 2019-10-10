package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository extends Repository<Usuario, Long> {
    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Optional<UsuarioResponse> queryById(Long id);

    Optional<Usuario> findByEmail(String email);

    @Query("select u from Usuario u where u.nomeCompleto like :nomeCompletoOuEmail% or u.email like :nomeCompletoOuEmail%")
    List<UsuarioResponse> findByNomeCompletoOrEmail(@Param("nomeCompletoOuEmail")String nomeCompletoOuEmail);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> save(Usuario usuario);

    void delete(Usuario usuario);
}
