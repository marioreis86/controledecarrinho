package com.varejista.controledecarrinho.controller;

import com.varejista.controledecarrinho.models.Cupom;
import com.varejista.controledecarrinho.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cupom")
public class CupomController {
    @Autowired
    private CupomService cupomService;

    @GetMapping()
    public ResponseEntity<List<Cupom>> findAll() {
        List<Cupom> cupoms = cupomService.findAll();
        return ResponseEntity.ok().body(cupoms);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cupom> find(@PathVariable Integer id) {
        Cupom cupom = cupomService.findById(id);
        return ResponseEntity.ok().body(cupom);
    }

    @PostMapping()
    public ResponseEntity<Cupom> insert(@RequestBody Cupom obj) {
        Cupom cupom = cupomService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cupom.getId()).toUri();

        return ResponseEntity.created(uri).body(cupom);
    }

    @PutMapping(value = "/{nome}")
    public ResponseEntity<Void> update(@RequestBody Cupom obj, @PathVariable String nome) {
        Cupom cupom = cupomService.findByNome(nome);
        obj.setId(cupom.getId());
        cupomService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cupom> delete(@PathVariable Integer id) {
        cupomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
