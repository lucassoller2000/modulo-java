package br.com.cwi.treinamento.java.locadora;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
public class Locadora {
    private List<Filme> filmes = new LinkedList<>();
    private List<Cliente> clientes = new LinkedList<>();
    private List<Pedido> pedidos = new LinkedList<>();

    public void adicionarFitas(String titulo, int numeroFitas, Categoria categoria){


        Filme filme = Filme.builder().titulo(titulo).categoria(categoria).build();

        List<Fita> fitas = new LinkedList<>();
        for (int i = 0; i < numeroFitas; i++) {
          fitas.add(Fita.builder().situacao(Situacao.DISPONIVEL).build());
        }
        filme.setFitas(fitas);
        filmes.add(filme);

    }

    public void adicionarCliente(String nomeCliente, String cpf){
        clientes.add(Cliente.builder().nome(nomeCliente).cpf(cpf).build());
    }

    public void locarFilme(List<String> nomeFilmes, String cpf){
        Optional<Cliente> c = clientes.stream().filter(cli -> cpf.equals(cli.getCpf())).findFirst();
        if(c.isPresent()) {
            Optional<Pedido> p = pedidos.stream().filter(ped -> cpf.equals(ped.getCpf())).findFirst();
                List<Filme> filmesNoPedido = new LinkedList<>();
                for (String nome : nomeFilmes) {
                    Optional<Filme> f = filmes.stream().filter(fil -> nome.equals(fil.getTitulo())).findFirst();
                    if (f.isPresent()) {
                        Filme filme = f.get();
                        Optional<Fita> fi = filme.getFitas().stream().filter(fit -> Situacao.DISPONIVEL.equals(fit.getSituacao())).findFirst();
                        if (fi.isPresent()) {
                            Fita fita = fi.get();
                            fita.setSituacao(Situacao.LOCADA);
                            filmesNoPedido.add(filme);
                        }
                        filme.criarPrazoDeEntrega(filme);
                        filme.criarDataDeEntrega();
                    }
                }
            if (p.isPresent()) {
                Optional<Pedido> pedido = pedidos.stream().filter(ped -> cpf.equals(ped.getCpf()) && !SituacaoPedido.PENDENTE.equals(ped.getSituacaoPedido())).findFirst();
                if(pedido.isPresent()){
                    pedidos.add(Pedido.builder().cpf(cpf).situacaoPedido(SituacaoPedido.PENDENTE).filmesLocados(filmesNoPedido).build());
                }
            }else{
                pedidos.add(Pedido.builder().cpf(cpf).situacaoPedido(SituacaoPedido.PENDENTE).filmesLocados(filmesNoPedido).build());
            }
        }

    }
    public void desalocarFilme(String nomeFilme, String cpf){
        Optional<Cliente> c = clientes.stream().filter(cli -> cpf.equals(cli.getCpf())).findFirst();
        if(c.isPresent()) {
            Optional<Pedido> p = pedidos.stream().filter(ped -> cpf.equals(ped.getCpf())).findFirst();
            if (p.isPresent()) {
                Pedido pedido = p.get();
                Optional<Filme> f = filmes.stream().filter(fil -> nomeFilme.equals(fil.getTitulo())).findFirst();
                if (f.isPresent()) {
                    Filme filme = f.get();
                    Optional<Fita> fi = filme.getFitas().stream().filter(fit -> Situacao.LOCADA.equals(fit.getSituacao())).findFirst();
                    if (fi.isPresent()) {
                        Fita fita = fi.get();
                        fita.setSituacao(Situacao.DISPONIVEL);
                    }
                }
                pedido.tornarAtivo(cpf, this);
            }
        }


    }

    public Pedido buscarPedido(String cpf){
        Optional<Pedido> p = pedidos.stream().filter(ped -> cpf.equals(ped.getCpf())).findFirst();
        if (p.isPresent()) {
            Pedido pedido = p.get();
            return pedido;
        }return null;
    }
}