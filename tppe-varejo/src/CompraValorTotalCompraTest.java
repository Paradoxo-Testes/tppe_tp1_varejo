import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.*;

@RunWith(Parameterized.class)
public class CompraValorTotalCompraTest {

    public Compra compra;
    protected String data;
    protected Cliente cliente;
    protected Cartao cartao;
    protected List<Produto> produtoVendido;
    protected String metodoPagamento;
    protected Imposto impostoGeral;
    protected double valorEsperado;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Produto> produtos = Arrays.asList(
                new Produto("1234abcd", "descrição teste tdd", 80.50, "m3"),
                new Produto("abcd1234", "descricao", 79.99, "ml"),
                new Produto("9874lkhj", "a", 100.90, "m3"),
                new Produto("agcr1284", "descrição teste tdd1", 1, "g")
        );

        Cartao cartaoUsuario1 = new Cartao("1111222233334444", "Titular Teste", "12/24", "123.456.789-00");
        Cartao cartaoUsuario2 = new Cartao("4296132233334444", "Titular Teste", "12/24", "123.456.789-00");


        return Arrays.asList(new Object[][]{
                {
                        "14/10/2024 15:33:00",
                        new Cliente("Caio Cardoso",
                                "567.541.880-65",
                                Cliente.Regiao.Sudeste,
                                true,
                                Cliente.Tipo.PRIME,
                                0.0,
                                0.0
                        ),
                        cartaoUsuario1,
                        produtos,
                        "CARTAO",
                        new Imposto("Distrito_Federal", 100),
                        262.39d
                },
                {
                        "14/10/2024 15:33:00",
                        new Cliente("Maiara Silva",
                                "478.385.280-46",
                                Cliente.Regiao.Centro_Oeste,
                                true,
                                Cliente.Tipo.ESPECIAL,
                                0.0,
                                0.0
                        ),
                        cartaoUsuario2,
                        produtos,
                        "CARTAO",
                        new Imposto("Distrito_Federal", 100),
                        218.83d
                },
                {
                        "14/10/2024 15:33:00",
                        new Cliente("João da Cunha",
                                "039.992.690-90",
                                Cliente.Regiao.Norte,
                                false,
                                Cliente.Tipo.ESPECIAL,
                                0.0,
                                0.0
                        ),
                        cartaoUsuario1,
                        produtos,
                        "DINHEIRO",
                        new Imposto("Distrito_Federal", 100),
                        251.90d
                },
                {
                        "14/10/2024 15:33:00",
                        new Cliente("Pedro Sampaio",
                                "505.314.460-50",
                                Cliente.Regiao.Nordeste,
                                true,
                                Cliente.Tipo.PADRAO,
                                0.0,
                                0.0
                        ),
                        cartaoUsuario2,
                        produtos,
                        "DINHEIRO",
                        new Imposto("Distrito_Federal", 100),
                        277.39d
                }
        });
    }

    public CompraValorTotalCompraTest(String data, Cliente cliente, Cartao cartao, List<Produto> produtoVendido, String metodoPagamento, Imposto impostoGeral, double valorEsperado) {
        this.data = data;
        this.cliente = cliente;
        this.cartao = cartao;
        this.produtoVendido = produtoVendido;
        this.metodoPagamento = metodoPagamento;
        this.impostoGeral = impostoGeral;
        this.valorEsperado = valorEsperado;
    }

    @Test
    public void testCalculoDoValorDaCompraTotal(){
        compra = new Compra(data, cliente, produtoVendido, metodoPagamento, impostoGeral);
        assertEquals( Compra.valorTotalCompra(compra.produtoVendido, compra.cliente, compra.metodoPagamento, cartao.getNumero()), valorEsperado,0.1);
    }

}