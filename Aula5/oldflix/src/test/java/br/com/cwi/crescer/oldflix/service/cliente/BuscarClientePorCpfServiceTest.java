package br.com.cwi.crescer.oldflix.service.cliente;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BuscarClientePorCpfServiceTest {

    @InjectMocks
    private SalvarClienteService salvarCliente;

    @InjectMocks
    private BuscarClientePorCpfService buscarClientePorCpfService;

    @Mock
    private IClienteRepository repository;

    @Test(expected = CpfInvalidoException.class)
    public void cpfInvalidoDeveGerarErro(){
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        salvarCliente.salvar(cliente);
    }

    @Test
    public void deveRetornarUmClienteExistente(){
        //cenario
        String cpf = "123";
        Cliente clienteNovo = new Cliente();
        clienteNovo.setCpf(cpf);

        //cenario - mocks
        Mockito.when(repository.findByCpf(cpf)).thenReturn(Optional.of(clienteNovo));

        //metodo que deve ser testado
        Cliente cliente = buscarClientePorCpfService.buscarClientePorCpf(cpf);

        //validacoes
        Assert.assertNotNull(cliente);
        Assert.assertEquals(cpf, cliente.getCpf());
    }
}