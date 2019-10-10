package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import br.com.cwi.crescer.tcc.repository.projection.PostResponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarPostUsuarioLogado {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public List<PostResponse> buscar(UserPrincipal userPrincipal){
        Usuario usuario= buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        return postRepository.queryByUsuario(usuario.getId());
    }
}
