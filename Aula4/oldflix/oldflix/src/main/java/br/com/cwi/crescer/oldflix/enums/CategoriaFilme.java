package br.com.cwi.crescer.oldflix.enums;

import lombok.Getter;

@Getter
public enum CategoriaFilme {
    DOURADA(10.00,2),
    PRATA(7.00,3),
    BRONZE(3.00,5);

    private double valor;
    private int prazoEntrega;

    CategoriaFilme(double valor, int prazoEntrega){
        this.valor = valor;
        this.prazoEntrega = prazoEntrega;
    }
}

