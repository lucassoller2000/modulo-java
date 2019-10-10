package br.com.cwi.crescer.oldflix.service.cliente;
import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarClientePorIdServiceTest {

    @InjectMocks
    private BuscarClientePorIdService tested;

    @Mock
    private IClienteRepository repository;

    @Test(expected = ClienteNaoCadastradoException.class)
    public void deveLancarUmErro(){
        Long id = 1L;
        tested.buscar(null);
    }

    @Test
    public void deveRetornarUmClienteExistente(){
        //cenario
        Long id = 1L;
        Cliente clienteNovo = new Cliente();
        clienteNovo.setId(id);

        //cenario - mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(clienteNovo));

        //metodo que deve ser testado
        Cliente cliente = tested.buscar(id);

        //validacoes
        Assert.assertNotNull(cliente);
        Assert.assertEquals(id, cliente.getId());
    }

}