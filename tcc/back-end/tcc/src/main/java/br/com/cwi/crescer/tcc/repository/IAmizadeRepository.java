package br.com.cwi.crescer.tcc.repository;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.projection.AmizadeReponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAmizadeRepository extends Repository<Amizade, Long> {

    List<Amizade> findAll();

    Optional<Amizade> findById(Long id);

    Optional<AmizadeReponse> findByUsuarioRemetenteAndUsuarioDestinatario(Usuario usuarioRemetente, Usuario usuarioDestinatario);

    Optional<Amizade> queryByUsuarioRemetenteAndUsuarioDestinatario(Usuario usuarioRemetente, Usuario usuarioDestinatario);

    @Query("select a from Amizade a where a.usuarioRemetente.nomeCompleto like :nomeCompletoOuEmail% or a.usuarioRemetente.email like :nomeCompletoOuEmail% and a.usuarioDestinatario.id = :id and a.statusAmizade like 'AMIGOS'")
    List<AmizadeReponse> queryByAmigos(@Param("nomeCompletoOuEmail")String nomeCompletoOuEmail, @Param("id")Long id);


    List<AmizadeReponse> findByUsuarioDestinatarioAndStatusAmizade(Usuario usuario, StatusAmizade statusAmizade);

    Optional<Amizade> save(Amizade amizade);

    void delete(Amizade amizade);
}
