package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.dominio.dto.CurtidaDto;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.post.BuscarPostPorIdService;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SalvarCurtidaServiceTest {

    @Mock
    ICurtidaRepository curtidaRepository;

    @Mock
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    BuscarPostPorIdService buscarPostPorIdService;

    @InjectMocks
    SalvarCurtidaService tested;

    @Captor
    ArgumentCaptor<Curtida> captadorDeCurtida;

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        CurtidaDto curtidaDto = new CurtidaDto();
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);
        tested.salvar(curtidaDto,userPrincipal);
    }

//    @Test
//    public void deveCurtir(){
//        Long idUsuario = 1L;
//        Long idPost = 2L;
//        CurtidaDto curtidaDto = new CurtidaDto();
//        curtidaDto.setIdPost(idPost);
//        List<GrantedAuthority> authorities = Arrays.asList();
//        UserPrincipal userPrincipal = new UserPrincipal(idUsuario, "Lucas", "Lucas", "lucas123", "123", authorities);
//
//        Usuario usuario = new Usuario();
//        Post post = new Post();
//
//        Mockito.when(buscarUsuarioPorIdService.buscar(userPrincipal.getId())).thenReturn(usuario);
//        Mockito.when(buscarPostPorIdService.buscar(curtidaDto.getIdPost())).thenReturn(post);
//
//        tested.salvar(curtidaDto,userPrincipal);
//
//        Mockito.verify(curtidaRepository, Mockito.times(1)).save(captadorDeCurtida.capture());
//        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(userPrincipal.getId());
//        Mockito.verify(buscarPostPorIdService, Mockito.times(1)).buscar(curtidaDto.getIdPost());
//
//        Curtida curtidaCapturada = captadorDeCurtida.getValue();
//
//        Assert.assertNotNull(curtidaCapturada);
//        Assert.assertSame(usuario, curtidaCapturada.getUsuario());
//        Assert.assertSame(post, curtidaCapturada.getPost());
//    }

}