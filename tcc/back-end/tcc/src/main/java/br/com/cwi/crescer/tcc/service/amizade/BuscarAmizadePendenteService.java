package br.com.cwi.crescer.tcc.service.amizade;


import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.repository.projection.AmizadeReponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarAmizadePendenteService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public List<AmizadeReponse> buscar(UserPrincipal userPrincipal){
        Usuario usuario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        List<AmizadeReponse> amizadesResponse = amizadeRepository.findByUsuarioDestinatarioAndStatusAmizade(usuario, StatusAmizade.PENDENTE);

        if(amizadesResponse.isEmpty()){
            throw new IllegalArgumentException("Você não possui nenhum pedido de amizade pendente");
        }

        return amizadesResponse;
    }

}
