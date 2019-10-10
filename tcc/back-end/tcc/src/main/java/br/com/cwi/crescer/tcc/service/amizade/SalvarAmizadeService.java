package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.AmizadeDto;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalvarAmizadeService {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void salvar(AmizadeDto amizadeDto, UserPrincipal userPrincipal){
        if(Objects.isNull(amizadeDto.getIdUsuario())){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Usuario usuarioRemetente = buscarUsuarioPorIdService.buscar(userPrincipal.getId());
        Usuario usuarioDestinatario = buscarUsuarioPorIdService.buscar(amizadeDto.getIdUsuario());

        if(!amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioRemetente, usuarioDestinatario).isPresent()
                && !amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioDestinatario, usuarioRemetente).isPresent()){

            Amizade amizade = new Amizade();
            amizade.setStatusAmizade(StatusAmizade.PENDENTE);
            amizade.setUsuarioRemetente(usuarioRemetente);
            amizade.setUsuarioDestinatario(usuarioDestinatario);
            amizadeRepository.save(amizade);
        }
    }
}
