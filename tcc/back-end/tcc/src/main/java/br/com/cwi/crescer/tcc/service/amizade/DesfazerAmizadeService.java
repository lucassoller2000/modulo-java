package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class DesfazerAmizadeService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarAmizadePorUsuarioRemetenteEUsuarioDestinatario buscarAmizade;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    BuscarAmizadePorIdService buscarAmizadePorIdService;


    public void remover(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }
        Amizade amizade = buscarAmizadePorIdService.buscar(id);

        if(amizade.getStatusAmizade().equals(StatusAmizade.AMIGOS)){
            Optional<Amizade> amizade1 = amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(amizade.getUsuarioDestinatario(), amizade.getUsuarioRemetente());
            if(amizade1.isPresent()){
                amizadeRepository.delete(amizade1.get());
            }else {
                Amizade amizade2 = amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(amizade.getUsuarioRemetente(), amizade.getUsuarioDestinatario()).get();
                amizadeRepository.delete(amizade2);
            }
        }
        amizadeRepository.delete(amizade);
    }
}