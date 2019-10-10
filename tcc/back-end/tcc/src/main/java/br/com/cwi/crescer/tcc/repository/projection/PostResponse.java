package br.com.cwi.crescer.tcc.repository.projection;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;

import java.time.LocalDate;
import java.util.List;

public interface PostResponse {
    Long getId();
    LocalDate getDataPostagem();
    String getPostagem();
    UsuarioResponse getUsuario();
    List<CurtidaResponse> getCurtidas();
    List<ComentarioResponse> getComentarios();
    PrivacidadePost getPrivacidadePost();
}
