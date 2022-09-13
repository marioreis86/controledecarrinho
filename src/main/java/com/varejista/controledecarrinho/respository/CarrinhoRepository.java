package com.varejista.controledecarrinho.respository;

import com.varejista.controledecarrinho.models.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    @Query(value = "SELECT * FROM carrinho c ORDER BY c.data_criacao", nativeQuery = true)
    List<Carrinho> findAllByUsuarioOrderByDataCriacao();

    @Query(value = "SELECT * FROM carrinho c WHERE c.finalizado = false", nativeQuery = true)
    List<Carrinho> findCarrinhoNaoFinalizado();
}