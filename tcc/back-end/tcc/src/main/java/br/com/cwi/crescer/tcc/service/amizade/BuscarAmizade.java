package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.repository.projection.AmizadeReponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarAmizade {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarAmizadePorUsuarioRemetenteEUsuarioDestinatario buscarAmizade;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;
    public AmizadeReponse buscar(Long id, UserPrincipal userPrincipal){

        Usuario usuarioRemetente = buscarUsuarioPorIdService.buscar(id);
        Usuario usuarioDestinatario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        Optional<AmizadeReponse> amizade = amizadeRepository.findByUsuarioRemetenteAndUsuarioDestinatario(usuarioRemetente, usuarioDestinatario);
        if(amizade.isPresent()){
            return amizade.get();
        }else if(!amizade.isPresent()){
            Optional<AmizadeReponse> amizade1 = amizadeRepository.findByUsuarioRemetenteAndUsuarioDestinatario(usuarioDestinatario, usuarioRemetente);
            if(amizade1.isPresent()){
                return amizade1.get();
            }
        }return null;

    }
}
