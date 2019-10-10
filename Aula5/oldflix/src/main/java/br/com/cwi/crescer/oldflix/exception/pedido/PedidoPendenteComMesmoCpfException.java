package br.com.cwi.crescer.oldflix.exception.pedido;

public class PedidoPendenteComMesmoCpfException extends RuntimeException{
    public PedidoPendenteComMesmoCpfException(){
        super("Um pedido com o mesmo CPF possui filmes pendente devolução");
    }
}
