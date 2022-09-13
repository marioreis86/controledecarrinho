package com.varejista.controledecarrinho.dto;

import lombok.*;

@AllArgsConstructor
@Getter @Setter
public class AdicionarAoCarrinhoDto {
    private Integer produtoId;
    private Integer quantidade;
}
