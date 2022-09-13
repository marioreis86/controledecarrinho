/*
package com.varejista.controledecarrinho.controller;

import com.varejista.controledecarrinho.models.Usuario;
import com.varejista.controledecarrinho.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<Set<Usuario>> findAll() {
        Set<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario produto = usuarioService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody Usuario obj) {
        Usuario produto = usuarioService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{nomeUsuario}")
    public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable String nomeUsuario) {
        Usuario produto = usuarioService.findByNomeUsuario(nomeUsuario);
        obj.setId(produto.getId());
        usuarioService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Usuario>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Usuario> list = usuarioService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
*/
