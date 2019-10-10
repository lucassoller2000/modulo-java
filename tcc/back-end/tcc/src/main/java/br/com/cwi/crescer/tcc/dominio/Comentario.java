package br.com.cwi.crescer.tcc.dominio;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMENTARIO")
public class Comentario {

    public static String getSEQUENCE() {
        return SEQUENCE;
    }

    private static final String SEQUENCE = "COMENTARIO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_COMENTARIO", nullable = false, precision = 10, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_POST")
    private Post post;

    @Column(name = "COMENTARIO", nullable = false, length = 200)
    private String comentario;

    @Column(name = "DATA_COMENTARIO", nullable = false, length = 256)
    private LocalDate dataComentario;

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

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataComentario() {
        return this.dataComentario;
    }

    public void setDataComentario(LocalDate dataComentario) {
        this.dataComentario = dataComentario;
    }


}
