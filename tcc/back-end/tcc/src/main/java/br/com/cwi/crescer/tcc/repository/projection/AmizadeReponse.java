package br.com.cwi.crescer.tcc.repository.projection;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;

public interface AmizadeReponse {
    Long getId();
    StatusAmizade getStatusAmizade();
    UsuarioResponse getUsuarioRemetente();
    UsuarioResponse getUsuarioDestinatario();
}
