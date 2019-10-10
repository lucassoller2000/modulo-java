package br.com.cwi.crescer.oldflix.dominio;

import br.com.cwi.crescer.oldflix.enums.CategoriaFilme;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "FILME")
public class Filme {

    private static final String SEQUENCE = "FILME_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_FILME", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 30, unique = true)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA_FILME", nullable = false, length = 7)
    private CategoriaFilme categoriaFilme;

    @JsonIgnore
    @OneToMany(mappedBy = "filme")
    private List<Fita> fitas;
}
