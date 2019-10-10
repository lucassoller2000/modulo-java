package br.com.cwi.crescer.oldflix.dominio;

import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "FITA")
public class Fita {

    private static final String SEQUENCE = "FITA_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_FITA", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 30)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_FITA", nullable = false, length = 10)
    private SituacaoFita situacaoFita;

    @ManyToOne
    @JoinColumn(name = "ID_FILME")
    private Filme filme;

    @JsonIgnore
    @OneToMany(mappedBy = "fita")
    private List<Locacao> locacoes;
}
