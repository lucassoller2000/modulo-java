package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.CurtidaDto;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.post.BuscarPostPorIdService;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SalvarCurtidaService {
    @Autowired
    ICurtidaRepository curtidaRepository;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void salvar(CurtidaDto curtidaDto, UserPrincipal userPrincipal){
        if(Objects.isNull(curtidaDto.getIdPost())){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Usuario usuario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        Post post = buscarPostPorIdService.buscar(curtidaDto.getIdPost());

        Optional<Curtida> curtidaUsuario = curtidaRepository.queryByUsuarioAndPost(usuario, post);
        if(curtidaUsuario.isPresent()){
            curtidaRepository.delete(curtidaUsuario.get());
        }else{
            Curtida curtida = new Curtida();
            curtida.setPost(post);
            curtida.setUsuario(usuario);
            curtidaRepository.save(curtida);
        }
    }
}
