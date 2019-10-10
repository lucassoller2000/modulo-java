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
public class BuscarAmizadeAceitaService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    private List<Usuario> amigos;

    public List<AmizadeReponse> buscar(UserPrincipal userPrincipal){
        Usuario usuario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        List<AmizadeReponse> amizadesReponseRemetente = amizadeRepository
                .findByUsuarioDestinatarioAndStatusAmizade(usuario, StatusAmizade.AMIGOS);

        if(amizadesReponseRemetente.isEmpty()){
            throw new IllegalArgumentException("Você não possui nenhum amigo");
        }

        return amizadesReponseRemetente;
    }
}
