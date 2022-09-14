package com.varejista.controledecarrinho;

import com.varejista.controledecarrinho.models.Carrinho;
import com.varejista.controledecarrinho.models.Cupom;
import com.varejista.controledecarrinho.models.Item;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.service.CarrinhoService;
import com.varejista.controledecarrinho.service.CupomService;
import com.varejista.controledecarrinho.service.ItemService;
import com.varejista.controledecarrinho.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = "com.varejista.controledecarrinho")
public class ControleDeCarrinhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeCarrinhoApplication.class, args);
	}

	//private static final Logger log = LoggerFactory.getLogger(ControleDeCarrinhoApplication.class);

	@Autowired
	private CarrinhoService carrinhoService;
	@Autowired
	private ProdutoService prodService;
	@Autowired
	private CupomService cupomService;

	@Autowired
	private ItemService itemService;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Carrinho carrinho = new Carrinho(null);

			Produto prod1 = new Produto(1, "teclado", 50.70);
			Produto prod2 = new Produto(2, "processador", 700.50);
			Produto prod3 = new Produto(3, "monitor", 1072.25);

			prodService.insert(prod1);
			prodService.insert(prod2);
			prodService.insert(prod3);

			Cupom cupom1 = new Cupom(1, "10DESCONTO", 0.10);
			Cupom cupom2 = new Cupom(2, "20DESCONTO", 0.20);
			Cupom cupom3 = new Cupom(3, "CUPOM90", 0.90);

			cupomService.insert(cupom1);
			cupomService.insert(cupom2);
			cupomService.insert(cupom3);

			Item item1 = new Item(carrinho, prod1, 10);
			Item item2 = new Item(carrinho, prod2, 4);
			Item item3 = new Item(carrinho, prod3, 2);

			/*itemService.insert(item1);
			itemService.insert(item2);
			itemService.insert(item3);*/

			List<Item> itens = List.of(item1, item2, item3);
			List<Cupom> cupons = List.of(cupom1, cupom2, cupom3);

			carrinho.setItens(itens);
			carrinho.setCupons(cupons);

			carrinho.setDataCriacao(LocalDateTime.now());
			carrinho.setDataAtualizacao(LocalDateTime.now().plusDays(2));

			//carrinhoService.insert(carrinho);
		};
	}
}
