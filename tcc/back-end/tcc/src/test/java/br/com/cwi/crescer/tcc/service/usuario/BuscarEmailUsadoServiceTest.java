package br.com.cwi.crescer.tcc.service.usuario;

import static org.junit.Assert.*;

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
public class BuscarEmailUsadoServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;

    @InjectMocks
    BuscarEmailUsadoService tested;

    @Test
    public void deveRetornarUsuarioExistente(){
        // Cenário
        String email = "123";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        // Cenário - Mocks
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscar(email);

        // Verificações
        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(email, usuarioRetornado.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emailNuloGeraErro(){
        tested.buscar(null);
    }
}