package com.varejista.controledecarrinho.service;

import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.respository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {
    @Autowired
    private ProdutoRepository prodRepository;

    public Set<Produto> findAll() {
        return prodRepository.findAll().stream().collect(Collectors.toSet());
    }
    public Produto findById(Integer id) {
        Optional<Produto> produto = prodRepository.findById(id);
        return produto.orElseThrow(() ->
                new ObjectNotFoundException("Produto com o Id["+id+"] não encontrado")
        );
    }

    public Produto findByNome(String nome) {
        Optional<Produto> produto = prodRepository.findByNome(nome);
        return produto.orElseThrow(() ->
                new ObjectNotFoundException("Produto \""+nome+"\" não encontrado")
        );
    }
    public Produto insert(Produto produto) {
        produto.setId(null);
        return prodRepository.save(produto);
    }

    public void delete(Integer id) {
        Produto produto = findById(id);
        prodRepository.delete(produto);
    }

    public Produto update(Produto produto) {
        Produto oldProduto = findById(produto.getId());
        produto.setId(oldProduto.getId());
        return prodRepository.save(produto);
    }

    public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return prodRepository.findAll(pageRequest);
    }
}
