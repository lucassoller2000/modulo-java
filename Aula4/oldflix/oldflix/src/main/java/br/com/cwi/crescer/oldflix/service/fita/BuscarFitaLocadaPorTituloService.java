package br.com.cwi.crescer.oldflix.service.fita;

import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.fita.FitaNaoCadastradaOuIndisponivelException;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarFitaLocadaPorTituloService {

    @Autowired
    IFitaRepository repository;

    private Fita fita;

    public Fita buscar(String titulo){
        List<Fita> fitas = repository.findByTitulo(titulo);

        if(fitas.size() == 0){
            throw new FilmeNaoCadastradoException();
        }
        Optional<Fita> fi = fitas.stream()
                .filter(f -> f.getSituacaoFita().equals(SituacaoFita.LOCADA))
                .findFirst();
        if(fi.isPresent()){
            fita = fi.get();
        }else{
            throw new FitaNaoCadastradaOuIndisponivelException();
        }
        return fita;
    }
}
