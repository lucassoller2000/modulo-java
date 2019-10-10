package br.com.cwi.crescer.oldflix.service.locacao;

import br.com.cwi.crescer.oldflix.dominio.*;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.enums.SituacaoPedido;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.fita.FitaNaoCadastradaException;
import br.com.cwi.crescer.oldflix.exception.locacao.LocacaoVaziaException;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoNaoAtivoException;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import br.com.cwi.crescer.oldflix.repository.ILocacaoRepository;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
import br.com.cwi.crescer.oldflix.service.filme.BuscarFilmePorTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DevolverFitaService {
    private Pedido pedido;

    @Autowired
    IPedidoRepository pedidoRepository;

    @Autowired
    IFitaRepository fitaRepository;

    @Autowired
    ILocacaoRepository locacaoRepository;

    @Autowired
    BuscarFilmePorTituloService buscarFilmePorTituloService;

    private Filme filme = new Filme();

    private BigDecimal divida;
    private BigDecimal valor;

    public void devolver(Cliente cliente, Fita fita){
        if(Objects.isNull(cliente)){
            throw new ClienteNaoCadastradoException();
        }

        if(Objects.isNull(fita)){
            throw new FitaNaoCadastradaException();
        }

        Optional<Pedido> ped = cliente.getPedidos().stream()
                .filter(p -> p.getSituacaoPedido().equals(SituacaoPedido.ATIVO))
                .findFirst();

        if(ped.isPresent()){
            pedido = ped.get();

            List<Locacao> locacoes = locacaoRepository.findAllByPedido(pedido);

            if(locacoes.isEmpty()){
                throw new LocacaoVaziaException();
            }

            for (Locacao loc: locacoes) {
                if(loc.getFita().getSituacaoFita().equals(SituacaoFita.LOCADA) && loc.getFita().getTitulo().equals(fita.getTitulo())){

                    Fita fitaAtualizada =loc.getFita();

                    filme = buscarFilmePorTituloService.buscar(fitaAtualizada.getTitulo());
                    valor = new BigDecimal(filme.getCategoriaFilme().getValor());
                    divida = pedido.getValorDivida().subtract(valor);

                    pedido.setValorDivida(divida);

                    if(divida.doubleValue() <= 0){
                        pedido.setSituacaoPedido(SituacaoPedido.ENCERRADO);
                    }

                    pedidoRepository.save(pedido);
                    fitaAtualizada.setSituacaoFita(SituacaoFita.DISPONIVEL);
                    fitaRepository.save(fitaAtualizada);
                    break;
                }
            }
        }else{
            throw new PedidoNaoAtivoException();
        }
    }
}