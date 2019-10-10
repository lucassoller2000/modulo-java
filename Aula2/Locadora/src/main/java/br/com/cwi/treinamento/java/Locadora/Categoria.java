package br.com.cwi.treinamento.java.locadora;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Categoria {
    DOURADA(10.00),
    PRATA(7.00),
    BRONZE(3.00);

    private double valor;

    Categoria(double valor){
        this.valor = valor;
    }
}
