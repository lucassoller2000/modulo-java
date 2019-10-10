package br.com.cwi.crescer.oldflix.web;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.dominio.Locacao;
import br.com.cwi.crescer.oldflix.dominio.Pedido;
import br.com.cwi.crescer.oldflix.dto.locacao.DevolucaoDto;
import br.com.cwi.crescer.oldflix.dto.locacao.LocacaoDto;
import br.com.cwi.crescer.oldflix.repository.ILocacaoRepository;
//import br.com.cwi.crescer.oldflix.service.pedido.BuscarPedidoDoClienteService;
import br.com.cwi.crescer.oldflix.service.cliente.BuscarClientePorCpfService;
import br.com.cwi.crescer.oldflix.service.fita.BuscarFitaDisponivelPorTituloService;
import br.com.cwi.crescer.oldflix.service.fita.BuscarFitaLocadaPorTituloService;
import br.com.cwi.crescer.oldflix.service.locacao.BuscarLocacaoPorIdService;
import br.com.cwi.crescer.oldflix.service.locacao.DevolverFitaService;
import br.com.cwi.crescer.oldflix.service.locacao.SalvarLocacaoService;
import br.com.cwi.crescer.oldflix.service.pedido.BuscarPedidoPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/locacao")
@RestController
public class LocacaoController {
    @Autowired
    ILocacaoRepository locacaoRepository;

    @Autowired
    BuscarLocacaoPorIdService buscarLocacaoPorIdService;

    @Autowired
    BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    BuscarFitaDisponivelPorTituloService buscarFitaDisponivelPorTituloService;

    @Autowired
    BuscarFitaLocadaPorTituloService buscarFitaLocadaPorTituloService;

    @Autowired
    BuscarClientePorCpfService buscarClientePorCpfService;

    @Autowired
    SalvarLocacaoService salvarLocacaoService;

    @Autowired
    DevolverFitaService devolverFitaService;

    @GetMapping
    public List<Locacao> listar(){
        return locacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Locacao buscarPorId(@PathVariable("id") Long id){
        return buscarLocacaoPorIdService.buscar(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody LocacaoDto locacaoDto){
        Pedido pedido = buscarPedidoPorIdService.buscar(locacaoDto.getIdPedido());
        Fita fita = buscarFitaDisponivelPorTituloService.buscar(locacaoDto.getTituloFilme());
        salvarLocacaoService.salvar(pedido, fita);
    }

    @PutMapping("/devolver")
    public void editar(@RequestBody DevolucaoDto devolucaoDto){
        Fita fita = buscarFitaLocadaPorTituloService.buscar(devolucaoDto.getTituloFilme());
        Cliente cliente = buscarClientePorCpfService.buscarClientePorCpf(devolucaoDto.getCpfCliente());
        devolverFitaService.devolver(cliente,fita);
    }
}
