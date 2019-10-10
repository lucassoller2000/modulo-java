package br.com.cwi.crescer.oldflix.service.locacao;
import br.com.cwi.crescer.oldflix.dominio.Locacao;
import br.com.cwi.crescer.oldflix.exception.locacao.LocacaoNaoCadastradaException;
import br.com.cwi.crescer.oldflix.repository.ILocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarLocacaoPorIdService {

    @Autowired
    ILocacaoRepository repository;

    public Locacao buscar (Long id){
        if(Objects.isNull(id)){
            throw new LocacaoNaoCadastradaException();
        }

        return repository
                .findById(id)
                .orElseThrow(()-> new LocacaoNaoCadastradaException());
    }
}