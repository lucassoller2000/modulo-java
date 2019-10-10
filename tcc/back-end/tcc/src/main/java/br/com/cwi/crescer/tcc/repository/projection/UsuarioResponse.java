package br.com.cwi.crescer.tcc.repository.projection;


public interface UsuarioResponse {
    Long getId();
    String getNomeCompleto();
    String getEmail();
    String getImagem();
    String getApelido();
}
