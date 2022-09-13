/*
package com.varejista.controledecarrinho.respository;

import com.varejista.controledecarrinho.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT u FROM usuario u WHERE u.nome_usuario LIKE %:nomeUsuario%")
    Optional<Usuario> findByNomeUsuario(@Param("nomeUsuario") String nomeUsuario);
}
*/
