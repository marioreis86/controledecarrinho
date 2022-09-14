package com.varejista.controledecarrinho.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cupom")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cupom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    @EqualsAndHashCode.Include
    private String nome;

    private Double desconto;
}
