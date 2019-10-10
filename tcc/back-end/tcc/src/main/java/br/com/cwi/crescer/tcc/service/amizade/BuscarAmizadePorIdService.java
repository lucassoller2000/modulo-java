package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarAmizadePorIdService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    public Amizade buscar(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        return amizadeRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Amizade não encontrada"));
    }
}
