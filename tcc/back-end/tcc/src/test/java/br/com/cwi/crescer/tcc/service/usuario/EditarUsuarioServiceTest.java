package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.EdicaoDto;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EditarUsuarioServiceTest {
    @Mock
    private IUsuarioRepository repository;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Captor
    ArgumentCaptor<Usuario> captadorDeUsuario;

    @InjectMocks
    private EditarUsuarioService tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberUsuarioNulo() {
        tested.editar(null,null);
    }


    @Test
    public void deveEditarUsuario() {

        EdicaoDto edicaoDto = new EdicaoDto();
        List<GrantedAuthority> authorities = Arrays.asList();
        UserPrincipal userPrincipal = new UserPrincipal(1L, "Lucas", "Lucas", "lucas123", "123", authorities);

        
        // Cenário
        Long id = 1L;
        String nomeCompleto = "Lucas";
        String apelido = "lucas";
        String email = "Lucas123";
        String imagem = "abc";

        edicaoDto.setNomeCompleto(nomeCompleto);
        edicaoDto.setApelido(apelido);
        edicaoDto.setImagem(imagem);

        Usuario usuario = new Usuario();
        Mockito.when(buscarUsuarioPorIdService.buscar(id)).thenReturn(usuario);

        // Execução
        tested.editar(userPrincipal, edicaoDto);

        usuario.setNomeCompleto(edicaoDto.getNomeCompleto());
        usuario.setApelido(edicaoDto.getApelido());
        usuario.setImagem(edicaoDto.getImagem());

        // Validações

        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(id);
        Mockito.verify(repository, Mockito.times(1)).save(captadorDeUsuario.capture());

        Usuario usuarioCapturado = captadorDeUsuario.getValue();

        Assert.assertEquals(nomeCompleto, usuarioCapturado.getNomeCompleto());
        Assert.assertEquals(imagem, usuarioCapturado.getImagem());
        Assert.assertEquals(apelido, usuarioCapturado.getApelido());

    }
}