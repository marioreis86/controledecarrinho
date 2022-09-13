package com.varejista.controledecarrinho.service;

import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import com.varejista.controledecarrinho.models.Cupom;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.respository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CupomService {
    @Autowired
    private CupomRepository cupomRepository;

    public Set<Cupom> findAll() {
        return cupomRepository.findAll().stream().collect(Collectors.toSet());
    }
    public Cupom findById(Integer id) {
        Optional<Cupom> cupom = cupomRepository.findById(id);
        return cupom.orElseThrow(() ->
                new ObjectNotFoundException("Cupom \"%s\" não encontrado")
        );
    }
    public Cupom insert(Cupom cupom) {
        cupom.setId(null);
        return cupomRepository.save(cupom);
    }

    public void delete(Integer id) {
        Cupom cupom = findById(id);
        cupomRepository.delete(cupom);
    }

    public Cupom update(Cupom cupom) {
        Cupom oldCupom = findById(cupom.getId());
        cupom.setId(oldCupom.getId());
        return cupomRepository.save(cupom);
    }
}
