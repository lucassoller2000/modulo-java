package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarCurtidaPorIdService {
    @Autowired
    ICurtidaRepository curtidaRepository;

    public Curtida buscar(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }
        return curtidaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Curtida não encontrada"));
    }
}
