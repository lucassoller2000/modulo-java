package br.com.cwi.crescer.tcc.dominio.dto;

public class PostDto {
    private Long id;

    private String postagem;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostagem() {
        return this.postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }
}
