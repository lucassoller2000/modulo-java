package br.com.cwi.crescer.tcc.service.amizade;
import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AceitarAmizadeService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarAmizadePorUsuarioRemetenteEUsuarioDestinatario buscarAmizade;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    BuscarAmizadePorIdService buscarAmizadePorIdService;

    public void aceitar(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Amizade amizade = buscarAmizadePorIdService.buscar(id);
        amizade.setStatusAmizade(StatusAmizade.AMIGOS);

        Amizade amizade1 = new Amizade();
        amizade1.setStatusAmizade(StatusAmizade.AMIGOS);
        amizade1.setUsuarioRemetente(amizade.getUsuarioDestinatario());
        amizade1.setUsuarioDestinatario(amizade.getUsuarioRemetente());

        amizadeRepository.save(amizade);
        amizadeRepository.save(amizade1);
    }
}
