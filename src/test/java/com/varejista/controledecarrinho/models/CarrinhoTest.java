package com.varejista.controledecarrinho.models;

import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CarrinhoTest {

    private Carrinho carrinho;

    private Produto prod1;
    private Produto prod2;
    private Produto prod3;

    private Item item1;
    private Item item2;
    private Item item3;

    private Cupom cupom1;
    private Cupom cupom2;
    private Cupom cupom3;

    List<Item> itens;
    List<Cupom> cupons;

    @BeforeEach void setup() {
        carrinho = new Carrinho(1);

        prod1 = new Produto(1, "teclado", 50.70);
        prod2 = new Produto(2, "processador", 700.50);
        prod3 = new Produto(3, "monitor", 1072.25);

        item1 = new Item(carrinho, prod1, 10);
        item2 = new Item(carrinho, prod2, 4);
        item3 = new Item(carrinho, prod3, 2);

        cupom1 = new Cupom(1, "10DESCONTO", 0.10);
        cupom2 = new Cupom(1, "20DESCONTO", 0.20);
        cupom3 = new Cupom(1, "CUPOM90", 0.90);

        itens = List.of(item1, item2, item3);
        cupons = List.of(cupom1, cupom2, cupom3);

        carrinho.setItens(itens);
        carrinho.setCupons(cupons);

        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setDataAtualizacao(LocalDateTime.now().plusDays(2));
    }

    @Test
    void deveTer3ItensNoCarrinho() {
        assertEquals(3, carrinho.getNumeroProdutos());
    }

    @Test
    void dataCriacaoEDataAtualizacaoDifereDe2Dias() {
        int dias = Period.between(carrinho.getDataCriacao().toLocalDate(), carrinho.getDataAtualizacao().toLocalDate()).getDays();
        assertTrue(dias >= 2);
    }

    @Test
    void verificaProdutosAdicionadosNoCarrinho() {
        assertEquals("teclado", carrinho.getItemPorNomeProduto("Teclado").getProduto().getNome());
        assertEquals("processador", carrinho.getItemPorNomeProduto("Processador").getProduto().getNome());
        assertEquals("monitor", carrinho.getItemPorNomeProduto("monitor").getProduto().getNome());
    }

    @Test
    void verificaSubTotal() {
        double subTotal1 = item1.getQuantidade() * prod1.getPreco();
        double subTotal2 = item2.getQuantidade() * prod2.getPreco();
        double subTotal3 = item3.getQuantidade() * prod3.getPreco();

        double[] totais = new double[] {subTotal1, subTotal2, subTotal3};

        assertArrayEquals(totais, new double[] {
                carrinho.getSubTotal(item1),
                carrinho.getSubTotal(item2),
                carrinho.getSubTotal(item3)
        });
    }

    @Test
    void verificaSubTotalComDesconto() {
        double subTotal1 = item1.getQuantidade() * prod1.getPreco();
        subTotal1 -= item1.getQuantidade() >= 10 ? subTotal1 * 0.1 : 0;

        double subTotal2 = item2.getQuantidade() * prod2.getPreco();
        subTotal2 -= item2.getQuantidade() >= 10 ? subTotal2 * 0.1 : 0;

        double subTotal3 = item3.getQuantidade() * prod3.getPreco();
        subTotal3 -= item3.getQuantidade() >= 10 ? subTotal3 * 0.1 : 0;

        double[] totais = new double[] {subTotal1, subTotal2, subTotal3};

        assertArrayEquals(totais, new double[] {
                carrinho.getSubTotalComDesconto(item1),
                carrinho.getSubTotalComDesconto(item2),
                carrinho.getSubTotalComDesconto(item3)
        });
    }

    @Test
    void verificaTotal() {
        double subTotal1 = item1.getQuantidade() * prod1.getPreco();
        double subTotal2 = item2.getQuantidade() * prod2.getPreco();
        double subTotal3 = item3.getQuantidade() * prod3.getPreco();

        double total = subTotal1 + subTotal2 + subTotal3;

        assertEquals(total, carrinho.getTotal());
    }

    @Test
    void verificaTotalComDesconto() {
        double subTotal1 = item1.getQuantidade() * prod1.getPreco();
        subTotal1 -= item1.getQuantidade() >= 10 ? subTotal1 * 0.1 : 0;

        double subTotal2 = item2.getQuantidade() * prod2.getPreco();
        subTotal2 -= item2.getQuantidade() >= 10 ? subTotal2 * 0.1 : 0;

        double subTotal3 = item3.getQuantidade() * prod3.getPreco();
        subTotal3 -= item3.getQuantidade() >= 10 ? subTotal3 * 0.1 : 0;

        double totalComDesconto = subTotal1 + subTotal2 + subTotal3;

        if (totalComDesconto >= 10_000)
            totalComDesconto -= totalComDesconto * 0.10;
        else if (totalComDesconto >= 5_000)
            totalComDesconto -= totalComDesconto * 0.07;
        else if (totalComDesconto >= 1_000)
            totalComDesconto -= totalComDesconto * 0.05;

        totalComDesconto = carrinho.aplicarCupomDesconto(totalComDesconto);

        assertEquals(totalComDesconto, carrinho.getTotalComDesconto());
    }

    @Test
    void aplicaCupomDeDesconto() {
        Cupom cupomMaiorDesconto = cupons.stream().max(Comparator.comparingDouble(Cupom::getDesconto)).get();
        double total = carrinho.getTotal() - (carrinho.getTotal() * cupomMaiorDesconto.getDesconto());

        assertEquals(total, carrinho.aplicarCupomDesconto(carrinho.getTotal()));
    }

    @Test
    void maiorCupomDeDesconto() {
        Cupom cupomMaiorDesconto = cupons.stream().max(Comparator.comparingDouble(Cupom::getDesconto)).get();

        assertTrue(cupomMaiorDesconto.getId() == cupom3.getId());
        assertTrue(cupomMaiorDesconto.getNome() == cupom3.getNome());
        assertTrue(cupomMaiorDesconto.getDesconto() == cupom3.getDesconto());
    }

    @Test
    void lancaExcecaoItemNaoEncontrado() {
        assertThrows(ObjectNotFoundException.class, () -> {
            carrinho.getItemPorNomeProduto("Wifi");
        });

        assertThrows(ObjectNotFoundException.class, () -> {
            carrinho.getSubTotal(new Item(new Carrinho(null), new Produto(null, "Pendrive", 37.90), null));
        });
    }
}