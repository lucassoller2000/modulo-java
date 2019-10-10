package br.com.cwi.crescer.tcc.dominio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "USUARIO")
public class Usuario {


    public static String getSEQUENCE() {
        return SEQUENCE;
    }

    private static final String SEQUENCE = "USUARIO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_USUARIO", nullable = false, precision = 10, unique = true)
    private Long id;


    @Column(name = "NOME_COMPLETO", nullable = false, length = 256)
    private String nomeCompleto;

    @Column(name = "APELIDO", length = 50)
    private String apelido;

    @Column(name = "EMAIL", nullable = false, length = 256, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "SENHA", nullable = false, length = 128)
    private String senha;

    @Column(name = "DATA_NASCIMENTO", nullable = false, length = 256)
    private LocalDate dataNascimento;

    @Column(name = "IMAGEM", length = 512)
    private String imagem;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Comentario> comentarios;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getImagem() {
        return this.imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Curtida> getCurtidas() {
        return this.curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
    }
}
