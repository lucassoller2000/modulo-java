package br.com.cwi.crescer.tcc.service.amizade;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.dominio.enumerated.StatusAmizade;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AceitarAmizadeServiceTest {
    @Mock
    IAmizadeRepository amizadeRepository;

    @Mock
    BuscarAmizadePorIdService buscarAmizadePorIdService;

    @InjectMocks
    AceitarAmizadeService tested;

    @Captor
    ArgumentCaptor<Amizade> captadorDeAmizade;

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.aceitar(null);
    }

    @Test
    public void deveAceitarAAmizade(){
        Long id = 1L;

        Amizade amizade = new Amizade();

        Mockito.when(buscarAmizadePorIdService.buscar(id)).thenReturn(amizade);

        tested.aceitar(id);

        Mockito.verify(amizadeRepository, Mockito.times(2)).save(captadorDeAmizade.capture());

        Amizade amizadeCapturada = captadorDeAmizade.getValue();

        Assert.assertEquals(StatusAmizade.AMIGOS, amizadeCapturada.getStatusAmizade());

    }
}