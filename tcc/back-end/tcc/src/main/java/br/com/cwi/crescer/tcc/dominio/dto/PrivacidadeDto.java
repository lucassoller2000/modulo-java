package br.com.cwi.crescer.tcc.dominio.dto;

import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;

public class PrivacidadeDto {
    PrivacidadePost privacidadePost;

    public PrivacidadePost getPrivacidadePost() {
        return this.privacidadePost;
    }

    public void setPrivacidadePost(PrivacidadePost privacidadePost) {
        this.privacidadePost = privacidadePost;
    }
}
