package br.com.cwi.crescer.tcc.repository.projection;

import java.time.LocalDate;

public interface ComentarioResponse {
    Long getId();
    String getComentario();
    LocalDate getDataComentario();
    UsuarioResponse getUsuario();
}
