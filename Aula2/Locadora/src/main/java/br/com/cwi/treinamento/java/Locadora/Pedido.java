package br.com.cwi.treinamento.java.locadora;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Optional;

@Builder
@Data
public class Pedido {
    private String cpf;
    private List<Filme> filmesLocados;
    private SituacaoPedido situacaoPedido;

    public double calcularValorTotal(String cpf, Locadora locadora){
        double valorTotal = 0;
        Pedido pedido = locadora.buscarPedido(cpf);
        for (Filme filme : pedido.getFilmesLocados()) {
            Optional<Fita> fi = filme.getFitas().stream().filter(fit -> Situacao.LOCADA.equals(fit.getSituacao())).findFirst();
            if(fi.isPresent()) {
                valorTotal += filme.getCategoria().getValor();
            }
        }
        return valorTotal;
    }


    public void tornarAtivo(String cpf, Locadora locadora){
        Pedido pedido = locadora.buscarPedido(cpf);
        for (Filme filme : pedido.getFilmesLocados()) {
            Optional<Fita> fi = filme.getFitas().stream().filter(fit -> Situacao.LOCADA.equals(fit.getSituacao())).findFirst();
            if(!fi.isPresent()) {
                pedido.setSituacaoPedido(SituacaoPedido.ATIVO);
            }
        }
    }

    public Filme buscarFilmeBronze(){
        Optional<Filme> filmeBronze = getFilmesLocados().stream().filter(f -> Categoria.BRONZE.equals(f.getCategoria())).findFirst();
        if(filmeBronze.isPresent()) {
            Filme bronze = filmeBronze.get();
            bronze.criarDataDeEntrega();
            return  bronze;
        }return null;
    }

    public Filme buscarFilmeDourado(){
        Optional<Filme> filmeDourado = getFilmesLocados().stream().filter(f -> Categoria.DOURADA.equals(f.getCategoria())).findFirst();
        if(filmeDourado.isPresent()) {
            Filme dourado = filmeDourado.get();
            return  dourado;
        }return null;
    }

    public Filme buscarFilmePrata(){
        Optional<Filme> filmePrata = getFilmesLocados().stream().filter(f -> Categoria.PRATA.equals(f.getCategoria())).findFirst();
        if(filmePrata.isPresent()) {
            Filme prata = filmePrata.get();
            return  prata;
        }return null;
    }

    public void calcularCombo(String cpf, Locadora locadora){
        int numeroPrata = 0;
        int numeroBronze = 0;
        Pedido pedido = locadora.buscarPedido(cpf);
        for (Filme filme : pedido.getFilmesLocados()) {
            if(filme.getCategoria().equals(Categoria.PRATA)){
                numeroPrata++;
            }else if(filme.getCategoria().equals(Categoria.BRONZE)){
                numeroBronze++;
            }
        }
            Filme prata = buscarFilmePrata();
            Filme bronze = buscarFilmeBronze();
            if (numeroPrata > numeroBronze && bronze!=null) {
                bronze.setPrazoDeEntrega(prata.getPrazoDeEntrega());
                bronze.criarDataDeEntrega();
            }
            else if (numeroPrata < numeroBronze && prata!=null) {
                prata.setPrazoDeEntrega(bronze.getPrazoDeEntrega());
                prata.criarDataDeEntrega();
            }

    }
}
