package br.com.cwi.treinamento.java.locadora;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Fita {
    private Situacao situacao;
}
