package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.PostDto;
import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
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
public class SalvarPostServiceTest {

    @Mock
    IPostRepository postRepository;

    @Mock
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @InjectMocks
    SalvarPostService tested;

    @Captor
    ArgumentCaptor<Post> captadorDePost;

    @Test(expected = IllegalArgumentException.class)
    public void postagemNulaGeraErro(){
        PostDto postDto = new PostDto();
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);
        tested.salvar(postDto,userPrincipal);
    }

    @Test
    public void devePostar(){
        String postagem = "post";
        Long idUsuario = 1L;
        PostDto postDto = new PostDto();
        postDto.setPostagem(postagem);
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(idUsuario, "Lucas", "Lucas", "lucas123", "123", authorities);

        Usuario usuario = new Usuario();

        Mockito.when(buscarUsuarioPorIdService.buscar(userPrincipal.getId())).thenReturn(usuario);

        tested.salvar(postDto,userPrincipal);

        Mockito.verify(postRepository, Mockito.times(1)).save(captadorDePost.capture());
        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(userPrincipal.getId());

        Post postCapturado = captadorDePost.getValue();

        Assert.assertNotNull(postCapturado);
        Assert.assertEquals(postDto.getPostagem(), postCapturado.getPostagem());
        Assert.assertEquals(LocalDate.now(), postCapturado.getDataPostagem());
        Assert.assertEquals(PrivacidadePost.PUBLICO, postCapturado.getPrivacidadePost());
        Assert.assertSame(usuario, postCapturado.getUsuario());
    }
}