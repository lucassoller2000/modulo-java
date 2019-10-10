package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.dto.AmizadeDto;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class SalvarAmizadeServiceTest {

    @Mock
    IAmizadeRepository amizadeRepository;

    @Mock
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @InjectMocks
    SalvarAmizadeService tested;

    @Captor
    ArgumentCaptor<Amizade> captadorDeAmizade;

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        AmizadeDto amizadeDto = new AmizadeDto();
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);
        tested.salvar(amizadeDto,userPrincipal);
    }

//    @Test
//    public void deveFazerAmigos(){
//        Long idUsuario = 1L;
//        Long idUsuarioAmigo = 2L;
//        AmizadeDto amizadeDto = new AmizadeDto();
//
//        amizadeDto.setIdUsuario(idUsuarioAmigo);
//        List<GrantedAuthority> authorities = Arrays.asList();
//        UserPrincipal userPrincipal = new UserPrincipal(idUsuario, "Lucas", "Lucas", "lucas123", "123", authorities);
//
//        Usuario usuarioRemetente = new Usuario();
//        Usuario usuarioDestinatario = new Usuario();
//        Amizade amizade = new Amizade();
//        amizade.setUsuarioRemetente(usuarioRemetente);
//        amizade.setUsuarioDestinatario(usuarioDestinatario);
//
//        Amizade amizade1 = new Amizade();
//        amizade.setUsuarioRemetente(usuarioDestinatario);
//        amizade.setUsuarioDestinatario(usuarioRemetente);
//
//        Mockito.when(buscarUsuarioPorIdService.buscar(userPrincipal.getId())).thenReturn(usuarioRemetente);
//        Mockito.when(buscarUsuarioPorIdService.buscar(amizadeDto.getIdUsuario())).thenReturn(usuarioRemetente);
//        Mockito.when(amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioRemetente,usuarioDestinatario)).thenReturn(Optional.of(amizade));
//        Mockito.when(amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioDestinatario,usuarioDestinatario)).thenReturn(Optional.of(amizade1));
//
//        tested.salvar(amizadeDto,userPrincipal);
//
//        Mockito.verify(amizadeRepository, Mockito.times(1)).save(captadorDeAmizade.capture());
//        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(userPrincipal.getId());
//        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(amizadeDto.getIdUsuario());
//
//
//        Amizade amizadeCapturado = captadorDeAmizade.getValue();
//
//        Assert.assertNotNull(amizadeCapturado);
//        Assert.assertEquals(StatusAmizade.PENDENTE, amizadeCapturado.getStatusAmizade());
//        Assert.assertSame(amizadeCapturado.getUsuarioRemetente(), usuarioRemetente); }

}