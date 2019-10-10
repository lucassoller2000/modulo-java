package br.com.cwi.treinamento.test;
import br.com.cwi.treinamento.java.locadora.Categoria;
import br.com.cwi.treinamento.java.locadora.Filme;
import br.com.cwi.treinamento.java.locadora.Locadora;
import br.com.cwi.treinamento.java.locadora.Pedido;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


public class LocadoraTest {

    private Locadora locadora = new Locadora();

    @BeforeEach
    public void setup(){
        locadora.adicionarCliente("Lucas","123");
        locadora.adicionarCliente("Joao", "456");
        locadora.adicionarFitas("O Rei Leão",3,Categoria.PRATA);
        locadora.adicionarFitas("Shrek",3, Categoria.BRONZE);
        locadora.adicionarFitas("Vingadores",2,Categoria.DOURADA);
    }
    @Test
    public void testarAdicionar3Filmes(){
        Locadora locadora = new Locadora();

        locadora.adicionarFitas("Filme",3, Categoria.DOURADA);

        Assert.assertEquals(3, locadora.getFilmes().get(0).getFitas().size());
    }

    @Test
    public void testarAdicionarClientes(){
        Assert.assertEquals(2, locadora.getClientes().size());
        Assert.assertEquals("Lucas", locadora.getClientes().get(0).getNome());
    }

    @Test
    public void testarBuscarPedidoComCpfInexistente(){
        Locadora locadora = new Locadora();
        Pedido pedido = locadora.buscarPedido("123");
        Assert.assertNull(pedido);
    }

    @Test
    public void testarNumeroDePedidos(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        locadora.locarFilme(filmes, "456");
        Assert.assertEquals(2, locadora.getPedidos().size());
    }

    @Test
    public void testarLocarUmFilmeInexistente(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Assert.assertEquals(0, pedido.getFilmesLocados().size());
    }

    @Test
    public void testarLocarFilmes(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Assert.assertEquals(2, pedido.getFilmesLocados().size());
    }

    @Test
    public void testarLocarFilmeLocado(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("O Rei Leão");
        filmes.add("O Rei Leão");
        locadora.locarFilme(filmes, "123");
        locadora.locarFilme(filmes, "456");
        Pedido pedido = locadora.buscarPedido("456");

        Assert.assertEquals(0, pedido.getFilmesLocados().size());
    }

    @Test
    public void testaeDesalocarFilmeEDepoisLocar(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        locadora.desalocarFilme("O Rei Leão", "123");
        locadora.locarFilme(filmes, "456");
        Pedido pedido = locadora.buscarPedido("456");
        Assert.assertEquals("O Rei Leão", pedido.getFilmesLocados().get(0).getTitulo());
    }

    @Test
    public void testarFazer2PedidosSemDevolverFitas(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        locadora.locarFilme(filmes, "123");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Assert.assertEquals(1, pedido.getFilmesLocados().size());
    }

    @Test
    public void testarLocarFilmesECompararValorTotal(){
        BigDecimal valorTotal;
        BigDecimal teste1 = new BigDecimal(17);
        BigDecimal teste2 = new BigDecimal(14);
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        valorTotal = new BigDecimal(pedido.calcularValorTotal("123",locadora));
        Assert.assertTrue(teste1.compareTo(valorTotal)==0);
        locadora.desalocarFilme("Shrek", "123");
        valorTotal = new BigDecimal(pedido.calcularValorTotal("123",locadora));
        Assert.assertTrue(teste2.compareTo(valorTotal)==0);
    }

    @Test
    public void testarSeOPrazoDeEntregaDeUmFilmeComCategoriaPrataE3(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Filme prata = pedido.buscarFilmePrata();
        Assert.assertEquals(3, prata.getPrazoDeEntrega());
    }

    @Test
    public void testarSeOPrazoDeEntregaDeUmFilmeComCategoriaDouradaE2(){
        List<String> filmes = new LinkedList<>();
        filmes.add("Vingadores");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Filme dourado = pedido.buscarFilmeDourado();
        Assert.assertEquals(2, dourado.getPrazoDeEntrega());
    }

    @Test
    public void testarCalcularComboComMaisCategoriaPrata(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        pedido.calcularCombo("123", locadora);
        Filme bronze = pedido.buscarFilmeBronze();
        Assert.assertEquals(3, bronze.getPrazoDeEntrega());
    }

    @Test
    public void testarCalcularComboComMaisCategoriaBronze(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        pedido.calcularCombo("123", locadora);
        Filme prata = pedido.buscarFilmePrata();
        Assert.assertEquals(5, prata.getPrazoDeEntrega());
    }

    @Test
    public void testarCalcularComboComMaisCategoriaDourado(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Vingadores");
        filmes.add("Vingadores");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        pedido.calcularCombo("123", locadora);
        Filme bronze = pedido.buscarFilmeBronze();
        Assert.assertEquals(5, bronze.getPrazoDeEntrega());
    }

    @Test
    public void testarCalcularComboComMesmaQuantidadeDeCategoriaPratEBronze(){
        List<String> filmes = new LinkedList<>();
        filmes.add("O Rei Leão");
        filmes.add("Shrek");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        pedido.calcularCombo("123", locadora);
        Filme bronze = pedido.buscarFilmeBronze();
        Filme prata = pedido.buscarFilmePrata();
        Assert.assertEquals(3, prata.getPrazoDeEntrega());
        Assert.assertEquals(5, bronze.getPrazoDeEntrega());
    }

    @Test
    public void testarSeADataDeEntregaDaCategoriaDouradaEDaquiA2Dias(){
        List<String> filmes = new LinkedList<>();
        LocalDate now = LocalDate.of(2018,06,23);
        filmes.add("Vingadores");
        locadora.locarFilme(filmes, "123");
        Pedido pedido = locadora.buscarPedido("123");
        Filme dourado = pedido.buscarFilmeDourado();
        Assert.assertEquals(now, dourado.getDataEntrega());
    }
}