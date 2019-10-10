package br.com.cwi.crescer.oldflix.service.locacao;
import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.dominio.Locacao;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.exception.fita.FitaNaoCadastradaException;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import br.com.cwi.crescer.oldflix.repository.ILocacaoRepository;
import br.com.cwi.crescer.oldflix.service.filme.BuscarFilmePorTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class SalvarLocacaoService {
    @Autowired
    ILocacaoRepository locacaoRepository;

    @Autowired
    IFitaRepository fitaRepository;

    @Autowired
    BuscarFilmePorTituloService buscarFilmePorTituloService;


    public void salvar(Pedido pedido, Fita fita){
        LocalDate now = LocalDate.now();
        Locacao locacao = new Locacao();

        if(Objects.isNull(pedido)){
            throw new PedidoNaoCadastradoException();
        }

        if(Objects.isNull(fita)){
            throw new FitaNaoCadastradaException();
        }

        Filme filme = buscarFilmePorTituloService.buscar(fita.getTitulo());

        locacao.setPrazoEntrega(filme.getCategoriaFilme().getPrazoEntrega());
        locacao.setFita(fita);
        locacao.setPedido(pedido);
        locacao.setDataEntrega(now.plusDays(filme.getCategoriaFilme().getPrazoEntrega()));
        locacaoRepository.save(locacao);
        fita.setSituacaoFita(SituacaoFita.LOCADA);
        fitaRepository.save(fita);
    }
}
