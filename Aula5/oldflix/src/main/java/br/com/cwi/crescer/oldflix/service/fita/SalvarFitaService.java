package br.com.cwi.crescer.oldflix.service.fita;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.filme.TituloInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import br.com.cwi.crescer.oldflix.service.filme.BuscarFilmePorTituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SalvarFitaService {

    @Autowired
    IFitaRepository repository;

    @Autowired
    BuscarFilmePorTituloService buscarFilmePorTituloService;

    public void salvar (Fita fita){
        if(Objects.isNull(fita.getTitulo()) || fita.getTitulo().isEmpty()){
            throw new TituloInvalidoException();
        }

        Filme filme = buscarFilmePorTituloService.buscar(fita.getTitulo());

        fita.setFilme(filme);
        fita.setSituacaoFita(SituacaoFita.DISPONIVEL);
        repository.save(fita);
    }
}
