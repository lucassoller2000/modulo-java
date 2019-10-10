package br.com.cwi.crescer.tcc.dominio;

import javax.persistence.*;

@Entity
@Table(name = "CURTIDA")
public class Curtida {

    public static String getSEQUENCE() {
        return SEQUENCE;
    }

    private static final String SEQUENCE = "CURTIDA_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_CURTIDA", nullable = false, precision = 10, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_POST")
    private Post post;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
