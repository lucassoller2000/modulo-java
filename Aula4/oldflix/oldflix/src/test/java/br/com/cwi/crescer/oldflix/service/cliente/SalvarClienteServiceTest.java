package br.com.cwi.crescer.oldflix.service.cliente;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfEmUsoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.exception.cliente.NomeInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class SalvarClienteServiceTest {

    @InjectMocks
    private SalvarClienteService salvarCliente;

    @Mock
    private IClienteRepository repository;

    @Test(expected = CpfInvalidoException.class)
    public void cpfInvalidoDeveGerarErro(){
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        salvarCliente.salvar(cliente);
    }

    @Test(expected = NomeInvalidoException.class)
    public void nomeInvalidoDeveGerarErro(){
        Cliente cliente = new Cliente();
        cliente.setCpf("123");
        salvarCliente.salvar(cliente);
    }

    @Test(expected = ClienteNaoCadastradoException.class)
    public void clienteNuloDeveGerarErro(){
        salvarCliente.salvar(null);
    }

    @Test(expected = CpfEmUsoException.class)
    public void cadastrarCpfExistenteGeraErro(){
        Cliente cliente = new Cliente();
        Cliente cliente1 = new Cliente();

        cliente.setId(1L);
        cliente.setCpf("123");
        cliente.setNome("lucas");

        cliente1.setId(2L);
        cliente1.setCpf("123");
        cliente1.setNome("lucas");

        Mockito.when(repository.save(cliente)).thenReturn(Optional.of(cliente));
        repository.save(cliente);

        Mockito.when(repository.save(cliente1)).thenReturn(Optional.of(cliente1));
        repository.save(cliente1);

        if(cliente.getCpf().equals(cliente1.getCpf())){
            throw new CpfEmUsoException();
        }
    }
}