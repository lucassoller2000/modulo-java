package br.com.cwi.crescer.oldflix.service.pedido;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.dominio.Locacao;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.enums.CategoriaFilme;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.enums.SituacaoPedido;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.locacao.LocacaoVaziaException;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import br.com.cwi.crescer.oldflix.repository.ILocacaoRepository;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
import br.com.cwi.crescer.oldflix.service.filme.BuscarFilmePorTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FecharPedidoService {
    @Autowired
    IPedidoRepository repository;

    @Autowired
    ILocacaoRepository locacaoRepository;

    @Autowired
    BuscarFilmePorTituloService buscarFilmePorTituloService;

    private Fita fita = new Fita();

    private Filme filme = new Filme();

    private List<Locacao> locacoes = new ArrayList<>();

    private double valorTotal = 0;

    private int prata = 0;
    private int bronze = 0;

    public void salvar (Pedido pedido){
        if(Objects.isNull(pedido)){
            throw new PedidoNaoCadastradoException();
        }

        locacoes = locacaoRepository.findAllByPedido(pedido);

        if(locacoes.isEmpty()){
            throw new LocacaoVaziaException();
        }

        for (Locacao loc: locacoes) {
            if(loc.getFita().getSituacaoFita().equals(SituacaoFita.LOCADA)){
                fita = loc.getFita();
                filme = buscarFilmePorTituloService.buscar(fita.getTitulo());
                valorTotal += filme.getCategoriaFilme().getValor();
                if(filme.getCategoriaFilme().equals(CategoriaFilme.BRONZE)){
                    bronze++;
                }else if(filme.getCategoriaFilme().equals(CategoriaFilme.PRATA)){
                    prata++;
                }
            }
        }

        for (Locacao loc: locacoes) {
            LocalDate now = LocalDate.now();
            fita = loc.getFita();
            filme = buscarFilmePorTituloService.buscar(fita.getTitulo());
            if(prata > bronze && filme.getCategoriaFilme().equals(CategoriaFilme.BRONZE)){
                loc.setPrazoEntrega(CategoriaFilme.PRATA.getPrazoEntrega());
                loc.setDataEntrega(now.plusDays(CategoriaFilme.PRATA.getPrazoEntrega()));
            }else if(bronze > prata && filme.getCategoriaFilme().equals(CategoriaFilme.PRATA)){
                loc.setPrazoEntrega(CategoriaFilme.BRONZE.getPrazoEntrega());
                loc.setDataEntrega(now.plusDays(CategoriaFilme.BRONZE.getPrazoEntrega()));
            }
            locacaoRepository.save(loc);
        }

        pedido.setValorTotal(new BigDecimal(valorTotal));
        pedido.setValorDivida(new BigDecimal(valorTotal));
        pedido.setSituacaoPedido(SituacaoPedido.ATIVO);
        repository.save(pedido);
        prata = 0;
        bronze = 0;
        valorTotal = 0;
    }
}
