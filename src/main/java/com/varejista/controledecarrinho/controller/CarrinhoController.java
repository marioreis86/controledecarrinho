package com.varejista.controledecarrinho.controller;

import com.varejista.controledecarrinho.dto.AdicionarAoCarrinhoDto;
import com.varejista.controledecarrinho.models.Carrinho;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService service;

    @PutMapping()
    public ResponseEntity<Carrinho> adicionarAoCarrinho(@RequestBody AdicionarAoCarrinhoDto dto
                                                        /*@RequestParam("nomeUsuario") String nomeUsuario,
                                                        @RequestParam("token") String token*/) {
        Carrinho carrinho = service.adicionarAoCarrinho(dto);
        return ResponseEntity.ok().body(carrinho);
    }

    @GetMapping()
    public ResponseEntity<List<Carrinho>> findAll() {
        List<Carrinho> carrinho = service.findAll();
        return ResponseEntity.ok().body(carrinho);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> find(@PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return ResponseEntity.ok().body(carrinho);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Carrinho> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/getSubtotal/{produto}/{id}")
    @ResponseBody
    public Double getSubtotal(@PathVariable String nomeProduto,
                             @PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return carrinho.getSubTotal(carrinho.getItemPorNomeProduto(nomeProduto));
    }

    @GetMapping(value = "/getSubtotalComDesconto/{produto}/{id}")
    @ResponseBody
    public Double getSubtotalComDesconto(@PathVariable String nomeProduto,
                              @PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return carrinho.getSubTotalComDesconto(carrinho.getItemPorNomeProduto(nomeProduto));
    }

    @GetMapping(value = "/getTotal/{id}")
    @ResponseBody
    public Double getSubtotal(@PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return carrinho.getTotal();
    }

    @GetMapping(value = "/getTotalComDesconto/{id}")
    @ResponseBody
    public Double getTotalComDesconto(@PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return carrinho.getTotalComDesconto();
    }



    @GetMapping(value = "/getNumeroProdutos/{id}")
    @ResponseBody
    public Integer getNumeroProdutos(@PathVariable Integer id) {
        Carrinho carrinho = service.findById(id);
        return carrinho.getNumeroProdutos();
    }
}
