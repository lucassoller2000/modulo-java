package br.com.cwi.crescer.oldflix.service.filme;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.CategoriaInvalidaException;
import br.com.cwi.crescer.oldflix.exception.filme.TituloEmUsoException;
import br.com.cwi.crescer.oldflix.exception.filme.TituloInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalvarFilmeService {
    @Autowired
    IFilmeRepository repository;

    public void salvar (Filme filme){
        if(Objects.isNull(filme.getCategoriaFilme())){
            throw new CategoriaInvalidaException();
        }
        if(Objects.isNull(filme.getTitulo()) || filme.getTitulo().isEmpty()){
            throw new TituloInvalidoException();
        }
        if(repository.findByTitulo(filme.getTitulo()).isPresent()){
            throw new TituloEmUsoException();
        }
        repository.save(filme);
    }
}
