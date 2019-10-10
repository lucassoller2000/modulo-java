package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarAmizadePorIdServiceTest {
    @Mock
    IAmizadeRepository amizadeRepository;

    @InjectMocks
    BuscarAmizadePorIdService tested;

    @Test
    public void deveRetornarAmizadeExistente(){
        // Cenário
        Long id = 1L;
        Amizade amizade = new Amizade();
        amizade.setId(id);

        // Cenário - Mocks
        Mockito.when(amizadeRepository.findById(id)).thenReturn(Optional.of(amizade));

        // Método que deve ser testado
        Amizade amizadeRetornado = tested.buscar(id);

        // Verificações
        Assert.assertNotNull(amizadeRetornado);
        Assert.assertEquals(id, amizadeRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarAmizadeGeraErro(){
        // Cenário
        Long id = 1L;
        // Cenário - Mocks
        Mockito.when(amizadeRepository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }
}