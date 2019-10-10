package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarAmizadePorUsuarioRemetenteEUsuarioDestinatario {
    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public Amizade buscar(Long id, Long idDestinatario){
        Usuario usuarioDestinatario = buscarUsuarioPorIdService.buscar(id);
        Usuario usuarioRemetente = buscarUsuarioPorIdService.buscar(id);
        return amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioRemetente,usuarioDestinatario)
                .orElseThrow(() -> new IllegalArgumentException("Amizade não encontrada"));
    }
}
