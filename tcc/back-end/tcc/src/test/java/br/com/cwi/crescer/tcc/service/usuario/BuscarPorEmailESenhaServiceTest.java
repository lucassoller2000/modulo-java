package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class BuscarPorEmailESenhaServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;

    @InjectMocks
    BuscarPorEmailESenhaService tested;

    @Test
    public void deveRetornarUsuarioExistente(){
        // Cenário
        String email = "123";
        String senha= "321";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        // Cenário - Mocks
        Mockito.when(usuarioRepository.findByEmailAndSenha(email,senha)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscar(email, senha);

        // Verificações
        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(email, usuarioRetornado.getEmail());
        Assert.assertEquals(senha, usuarioRetornado.getSenha());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarUsuarioGeraErro(){
        // Cenário
        String email = "123";
        String senha = "321";
        // Cenário - Mocks
        Mockito.when(usuarioRepository.findByEmailAndSenha(email,senha)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(email, senha);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emailNuloGeraErro(){
        tested.buscar(null, "123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void senhaNulaGeraErro(){
        tested.buscar("123", null);
    }
}