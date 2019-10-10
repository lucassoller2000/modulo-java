package br.com.cwi.crescer.oldflix.exception.cliente;

public class ClienteSemPedidoException extends RuntimeException {
    public ClienteSemPedidoException(){
        super("Não há nenhum pedido cadastrado com esse CPF");
    }
}
