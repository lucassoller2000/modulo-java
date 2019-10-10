package br.com.cwi.treinamento.java.locadora;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum Situacao {
    LOCADA,
    DISPONIVEL
}
