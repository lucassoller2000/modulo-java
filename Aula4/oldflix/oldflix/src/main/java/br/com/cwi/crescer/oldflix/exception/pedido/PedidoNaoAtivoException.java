package br.com.cwi.crescer.oldflix.exception.pedido;

public class PedidoNaoAtivoException extends RuntimeException {
    public PedidoNaoAtivoException(){
        super("Não foi encontrado nenhum pedido ativo com este CPF");
    }
}
