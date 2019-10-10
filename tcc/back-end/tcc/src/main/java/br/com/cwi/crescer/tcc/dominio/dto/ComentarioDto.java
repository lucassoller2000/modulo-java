package br.com.cwi.crescer.tcc.dominio.dto;


public class ComentarioDto {
    Long idPost;

    String comentario;

    public Long getIdPost() {
        return this.idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
