package br.com.cwi.crescer.oldflix.exception.locacao;

public class LocacaoVaziaException extends RuntimeException {
    public LocacaoVaziaException(){
        super("Escolha algum filme antes de fechar o pedido");
    }
}
