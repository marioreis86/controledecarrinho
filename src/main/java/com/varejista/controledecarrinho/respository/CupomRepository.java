package com.varejista.controledecarrinho.respository;

import com.varejista.controledecarrinho.models.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CupomRepository extends JpaRepository<Cupom, Integer> {
    Optional<Cupom> findByNome(String nome);
}
