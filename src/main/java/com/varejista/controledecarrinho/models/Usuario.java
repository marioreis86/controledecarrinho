/*
package com.varejista.controledecarrinho.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor @AllArgsConstructor
@Data
public class Usuario implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull @Column(unique = true)
    private String nomeUsuario;

    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "id.carrinho")
    private List<Carrinho> carrinhos;

    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDeCriacao;

    private String primeiroNome;
    private String ultimoNome;
}
*/
