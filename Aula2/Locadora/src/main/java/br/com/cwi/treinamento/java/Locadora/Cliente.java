package br.com.cwi.treinamento.java.locadora;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Builder
public class Cliente {

    private String cpf;

    private String nome;


}
