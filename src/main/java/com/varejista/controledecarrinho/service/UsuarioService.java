/*
package com.varejista.controledecarrinho.service;

import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import com.varejista.controledecarrinho.models.Produto;
import com.varejista.controledecarrinho.models.Usuario;
import com.varejista.controledecarrinho.models.Usuario;
import com.varejista.controledecarrinho.respository.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuRepo;

    public Set<Usuario> findAll() {
        return usuRepo.findAll().stream().collect(Collectors.toSet());
    }
    public Usuario findByNomeUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuRepo.findByNomeUsuario(nomeUsuario);
        return usuario.orElseThrow(() ->
                new ObjectNotFoundException("Usuario \""+nomeUsuario+"\" não encontrado")
        );
    }

    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = usuRepo.findById(id);
        return usuario.orElseThrow(() ->
                new ObjectNotFoundException("Usuario com o Id["+id+"] não encontrado")
        );
    }
    public Usuario insert(Usuario usuario) {
        usuario.setId(null);
        return usuRepo.save(usuario);
    }

    public void delete(Integer id) {
        Usuario usuario = findById(id);
        usuRepo.delete(usuario);
    }

    public Usuario update(Usuario usuario) {
        Usuario newUsuario = findById(usuario.getId());
        newUsuario.setId(null);
        return usuRepo.save(usuario);
    }

    public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return usuRepo.findAll(pageRequest);
    }
}
*/
