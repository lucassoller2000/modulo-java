package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import br.com.cwi.crescer.tcc.repository.projection.PostResponse;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuscarPostsPorUsuario {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public List<PostResponse> buscar(Long id){

        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Usuario usuario = buscarUsuarioPorIdService.buscar(id);

        return postRepository.findByUsuario(usuario.getId());
    }
}
