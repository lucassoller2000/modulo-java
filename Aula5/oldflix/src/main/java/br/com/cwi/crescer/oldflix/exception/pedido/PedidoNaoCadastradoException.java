package br.com.cwi.crescer.oldflix.exception.pedido;

public class PedidoNaoCadastradoException extends RuntimeException{

    public PedidoNaoCadastradoException(){
        super("Pedido não cadastrado");
    }
}
