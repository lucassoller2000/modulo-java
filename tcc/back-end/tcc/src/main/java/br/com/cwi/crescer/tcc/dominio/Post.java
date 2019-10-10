package br.com.cwi.crescer.tcc.dominio;

import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "POST")
public class Post {
    public static String getSEQUENCE() {
        return SEQUENCE;
    }

    private static final String SEQUENCE = "POST_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_POST", nullable = false, precision = 10, unique = true)
    private Long id;


    @Column(name = "POSTAGEM", nullable = false, length = 500)
    private String postagem;

    @Column(name = "DATA_POSTAGEM", nullable = false, length = 256)
    private LocalDate dataPostagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIVACIDADE", nullable = false, length = 13)
    private PrivacidadePost privacidadePost;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Curtida> curtidas;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Comentario> comentarios;

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

    public LocalDate getDataPostagem() {
        return this.dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public PrivacidadePost getPrivacidadePost() {
        return this.privacidadePost;
    }

    public void setPrivacidadePost(PrivacidadePost privacidadePost) {
        this.privacidadePost = privacidadePost;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Curtida> getCurtidas() {
        return this.curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
