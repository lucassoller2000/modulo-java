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
public class BuscarUsuarioPorIdServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;
    
    @InjectMocks
    BuscarUsuarioPorIdService tested;
    
    @Test
    public void deveRetornarUsuarioExistente(){
        // Cenário
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        // Cenário - Mocks
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscar(id);

        // Verificações
        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(id, usuarioRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarUsuarioGeraErro(){
        // Cenário
        Long id = 1L;
        // Cenário - Mocks
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }
}