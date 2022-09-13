package com.varejista.controledecarrinho.respository;

import com.varejista.controledecarrinho.models.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository  extends JpaRepository<Cupom, Integer> {
}
