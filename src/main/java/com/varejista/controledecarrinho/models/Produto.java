package com.varejista.controledecarrinho.models;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    @NotNull
    @EqualsAndHashCode.Include
    @NotEmpty(message = "O nome não pode ser vazio")
    @Length(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
    private String nome;

    @NotNull
    private Double preco;
}
