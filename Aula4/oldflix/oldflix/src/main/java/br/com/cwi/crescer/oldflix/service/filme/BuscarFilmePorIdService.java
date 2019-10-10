package br.com.cwi.crescer.oldflix.service.filme;
import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarFilmePorIdService {

    @Autowired
    IFilmeRepository repository;

    public Filme buscar (Long id){
        if(Objects.isNull(id)){
            throw new FilmeNaoCadastradoException();
        }
        return repository
                .findById(id)
                .orElseThrow(()-> new FilmeNaoCadastradoException());
    }
}
