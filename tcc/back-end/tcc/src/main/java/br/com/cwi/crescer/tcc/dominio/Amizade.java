package br.com.cwi.crescer.tcc.dominio;

import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;

import javax.persistence.*;
@Entity
@Table(name = "AMIZADE")
public class Amizade {
    public static String getSEQUENCE() {
        return SEQUENCE;
    }

    private static final String SEQUENCE = "AMIZADE_SEQ";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_AMIZADE", nullable = false, precision = 10, unique = true)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_REMETENTE")
    private Usuario usuarioRemetente;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_DESTINATARIO")
    private Usuario usuarioDestinatario;


    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private StatusAmizade statusAmizade;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuarioRemetente() {
        return this.usuarioRemetente;
    }

    public void setUsuarioRemetente(Usuario usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    public Usuario getUsuarioDestinatario() {
        return this.usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public StatusAmizade getStatusAmizade() {
        return this.statusAmizade;
    }

    public void setStatusAmizade(StatusAmizade statusAmizade) {
        this.statusAmizade = statusAmizade;
    }
    

}
