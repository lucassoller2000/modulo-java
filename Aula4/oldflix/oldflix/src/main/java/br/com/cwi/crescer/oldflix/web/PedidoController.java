package br.com.cwi.crescer.oldflix.web;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.dto.pedido.PedidoDto;
import br.com.cwi.crescer.oldflix.exception.pedido.PedidoNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IPedidoRepository;
//import br.com.cwi.crescer.oldflix.service.pedido.BuscarPedidoDoClienteService;
import br.com.cwi.crescer.oldflix.service.cliente.BuscarClientePorCpfService;
import br.com.cwi.crescer.oldflix.service.cliente.BuscarClientePorIdService;
import br.com.cwi.crescer.oldflix.service.pedido.BuscarPedidoDoClienteService;
import br.com.cwi.crescer.oldflix.service.pedido.BuscarPedidoPorIdService;
import br.com.cwi.crescer.oldflix.service.pedido.AbrirPedidoService;
import br.com.cwi.crescer.oldflix.service.pedido.FecharPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pedido")
@RestController
public class PedidoController {
    @Autowired
    private IPedidoRepository repository;

    @Autowired
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    private BuscarClientePorCpfService buscarClientePorCpfService;

    @Autowired
    private AbrirPedidoService abrirPedidoService;

    @Autowired
    private FecharPedidoService fecharPedidoService;

    @Autowired
    private BuscarPedidoDoClienteService buscarPedidoDoClienteService;


    @GetMapping
    public List<Pedido> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable("id") Long id){
        return buscarPedidoPorIdService.buscar(id);
    }

    @GetMapping("/cliente/{cpf}")
    public List<Pedido> buscarPedidosDoCliente(@PathVariable("cpf") String cpf){
        return buscarPedidoDoClienteService.buscar(cpf);
    }

    @PostMapping("/abrirPedido")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody PedidoDto pedidoDto){
        Cliente cliente = buscarClientePorCpfService.buscarClientePorCpf(pedidoDto.getCpfCliente());
        abrirPedidoService.salvar(cliente);
    }

    @PutMapping("/fecharPedido/{id}")
    public void fecharPedido(@PathVariable("id") Long id){
        Pedido pedido = buscarPedidoPorIdService.buscar(id);
        fecharPedidoService.salvar(pedido);
    }
}
