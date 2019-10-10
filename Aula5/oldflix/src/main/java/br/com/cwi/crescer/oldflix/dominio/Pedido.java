package br.com.cwi.crescer.oldflix.dominio;

import br.com.cwi.crescer.oldflix.enums.SituacaoPedido;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PEDIDO")
public class Pedido {
    private static final String SEQUENCE = "PEDIDO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_PEDIDO", nullable = false, precision = 10, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_PEDIDO", nullable = false, length = 9)
    private SituacaoPedido situacaoPedido;

    @Column(name = "VALOR_TOTAL", nullable = false, precision = 9, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "VALOR_DIVIDA", nullable = false, precision = 9, scale = 2)
    private BigDecimal valorDivida;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<Locacao> locacoes;
}
