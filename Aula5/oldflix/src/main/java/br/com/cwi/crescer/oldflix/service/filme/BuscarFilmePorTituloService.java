package br.com.cwi.crescer.oldflix.service.filme;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarFilmePorTituloService {

    @Autowired
    IFilmeRepository filmeRepository;

    public Filme buscar(String titulo){
        if(Objects.isNull(titulo)){
            throw new FilmeNaoCadastradoException();
        }
        return filmeRepository
                .findByTitulo(titulo)
                .orElseThrow(() -> new FilmeNaoCadastradoException());
    }
}
