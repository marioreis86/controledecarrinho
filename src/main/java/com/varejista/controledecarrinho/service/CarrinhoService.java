package com.varejista.controledecarrinho.service;

import com.varejista.controledecarrinho.dto.AdicionarAoCarrinhoDto;
import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import com.varejista.controledecarrinho.models.Carrinho;
import com.varejista.controledecarrinho.models.Item;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.respository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarrinhoService {
    @Autowired
    private ProdutoService prodService;
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Carrinho adicionarAoCarrinho(AdicionarAoCarrinhoDto dto) {
        Produto produto = prodService.findById(dto.getProdutoId());

        List<Carrinho> carrinhos = carrinhoRepository
                .findCarrinhoNaoFinalizado()
                .stream()
                .collect(Collectors.toList());

        Carrinho carrinho = carrinhos
                .stream()
                .findFirst().get();

        Optional<Item> first = carrinho.getItens()
                .stream()
                .filter(item -> item.getProduto().getNome().equalsIgnoreCase(produto.getNome()))
                .findFirst();

        first.ifPresent(item -> item.addQuantidade(dto.getQuantidade()));
        if (first.isEmpty()) {
            carrinho.getItens().add(new Item(carrinho, produto, dto.getQuantidade()));
        }

        return carrinhoRepository.save(carrinho);
    }

    public List<Carrinho> findAll() {
        return carrinhoRepository.findAll();
    }
    public Carrinho findById(Integer id) {
        Optional<Carrinho> carrinho = carrinhoRepository.findById(id);
        return carrinho.orElseThrow(() ->
                new ObjectNotFoundException("Carrinho com o Id["+id+"] não encontrado")
        );
    }

    public Iterable<Carrinho> getAllCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public Carrinho insert(Carrinho carrinho) {
        carrinho.setId(null);
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho update(Carrinho carrinho) {
        Carrinho oldCarrinho = findById(carrinho.getId());
        carrinho.setId(oldCarrinho.getId());
        return carrinhoRepository.save(carrinho);
    }

    public void delete(Integer id) {
        Carrinho carrinho = findById(id);
        carrinhoRepository.delete(carrinho);
    }
}
