package br.com.cwi.crescer.oldflix.service.fita;

import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.exception.fita.FitaNaoCadastradaException;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarFitaPorIdService {

    @Autowired
    IFitaRepository repository;

    public Fita buscar (Long id){
        if(Objects.isNull(id)){
            throw new FitaNaoCadastradaException();
        }
        return repository
                .findById(id)
                .orElseThrow(()-> new FitaNaoCadastradaException());
    }
}