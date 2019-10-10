package br.com.cwi.crescer.tcc.service.comentario;

import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.ComentarioDto;
import br.com.cwi.crescer.tcc.repository.IComentarioRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.post.BuscarPostPorIdService;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class SalvarComentarioService {
    @Autowired
    IComentarioRepository comentarioRepository;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void salvar(ComentarioDto comentarioDto, UserPrincipal userPrincipal){
        if(Objects.isNull(comentarioDto.getIdPost())){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        if(Objects.isNull(comentarioDto.getComentario()) || comentarioDto.getComentario().isEmpty()){
            throw new IllegalArgumentException("O comentário não pode fica em branco");
        }

        Usuario usuario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        Post post = buscarPostPorIdService.buscar(comentarioDto.getIdPost());

        Comentario comentario = new Comentario();

        comentario.setPost(post);
        comentario.setUsuario(usuario);
        comentario.setDataComentario(LocalDate.now());
        comentario.setComentario(comentarioDto.getComentario());

        comentarioRepository.save(comentario);
    }
}
