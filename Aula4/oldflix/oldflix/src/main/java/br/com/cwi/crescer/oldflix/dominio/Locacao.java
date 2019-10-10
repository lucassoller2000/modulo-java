package br.com.cwi.crescer.oldflix.dominio;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "LOCACAO")
public class Locacao {
    private static final String SEQUENCE = "LOCACAO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_LOCACAO", nullable = false, precision = 10, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ID_FITA")
    private Fita fita;

    @Column(name = "PRAZO_ENTREGA", nullable = false)
    private int prazoEntrega;

    @Column(name = "DATA_ENTREGA", nullable = false)
    private LocalDate dataEntrega;
}
