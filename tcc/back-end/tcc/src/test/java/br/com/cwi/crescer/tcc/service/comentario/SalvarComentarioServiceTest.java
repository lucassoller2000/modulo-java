package br.com.cwi.crescer.tcc.service.comentario;

import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.ComentarioDto;
import br.com.cwi.crescer.tcc.repository.IComentarioRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.post.BuscarPostPorIdService;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SalvarComentarioServiceTest {

    @Mock
    IComentarioRepository comentarioRepository;

    @Mock
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    BuscarPostPorIdService buscarPostPorIdService;

    @InjectMocks
    SalvarComentarioService tested;

    @Captor
    ArgumentCaptor<Comentario> captadorDeComentario;

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        ComentarioDto comentarioDto = new ComentarioDto();
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);
        tested.salvar(comentarioDto,userPrincipal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void comentarioNuloGeraErro(){
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setIdPost(1L);
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);
        tested.salvar(comentarioDto,userPrincipal);
    }

    @Test
    public void deveComentar(){
        String comentarioFeito = "comentario";
        Long idUsuario = 1L;
        Long idPost = 2L;
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setComentario(comentarioFeito);
        comentarioDto.setIdPost(idPost);
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(idUsuario, "Lucas", "Lucas", "lucas123", "123", authorities);

        Usuario usuario = new Usuario();
        Post post = new Post();

        Mockito.when(buscarUsuarioPorIdService.buscar(userPrincipal.getId())).thenReturn(usuario);
        Mockito.when(buscarPostPorIdService.buscar(comentarioDto.getIdPost())).thenReturn(post);

        tested.salvar(comentarioDto,userPrincipal);

        Mockito.verify(comentarioRepository, Mockito.times(1)).save(captadorDeComentario.capture());
        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(userPrincipal.getId());
        Mockito.verify(buscarPostPorIdService, Mockito.times(1)).buscar(comentarioDto.getIdPost());

        Comentario comentarioCapturado = captadorDeComentario.getValue();

        Assert.assertNotNull(comentarioFeito);
        Assert.assertEquals(comentarioDto.getComentario(), comentarioCapturado.getComentario());
        Assert.assertEquals(LocalDate.now(), comentarioCapturado.getDataComentario());
        Assert.assertSame(usuario, comentarioCapturado.getUsuario());
        Assert.assertSame(post, comentarioCapturado.getPost());
    }

}