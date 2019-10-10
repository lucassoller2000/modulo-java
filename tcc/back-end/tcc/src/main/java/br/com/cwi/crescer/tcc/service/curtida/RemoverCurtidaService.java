package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RemoverCurtidaService {

    @Autowired
    BuscarCurtidaPorIdService buscarCurtidaPorIdService;

    @Autowired
    ICurtidaRepository curtidaRepository;

    public void remover(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }
        Curtida curtida = buscarCurtidaPorIdService.buscar(id);

        curtidaRepository.delete(curtida);
    }
}
