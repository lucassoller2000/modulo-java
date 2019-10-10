package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.repository.projection.AmizadeReponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarAmigosPorNomeCompletoOuEmail {
    @Autowired
    IAmizadeRepository amizadeRepository;

    public List<AmizadeReponse> buscar(String nomeCompletoOuEmail, UserPrincipal userPrincipal){
        List<AmizadeReponse> amigos = amizadeRepository.queryByAmigos(nomeCompletoOuEmail, userPrincipal.getId());

        if(amigos.isEmpty()){
            throw new IllegalArgumentException("Nenhum resultado encontrado");
        }

        return amigos;
    }
}
