package br.com.cwi.crescer.oldflix.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    private static final String SEQUENCE = "CLIENTE_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "ID_CLIENTE", nullable = false, precision = 10, unique = true)
    private Long id;

    @Column(name = "CPF", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "NOME", nullable = false, length = 30)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
