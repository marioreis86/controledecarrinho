package com.varejista.controledecarrinho.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "item")
public class Item implements Serializable {
    @NotNull @Min(1)
    private Integer quantidade;

    @EmbeddedId
    @JsonIgnore
    private CarrinhoProdutoPK carrinhoProdutoPK;

    public Item(Carrinho carrinho, Produto produto, Integer quantidade) {
        carrinhoProdutoPK = new CarrinhoProdutoPK(carrinho, produto);
        this.quantidade = quantidade;
    }

    @Transient
    public Produto getProduto() {
        return carrinhoProdutoPK.getProduto();
    }
    @Transient
    public void addQuantidade(int adicao) {
        quantidade += adicao;
    }

    @Transient
    public Double getSubTotal() {
        return getQuantidade() * this.getProduto().getPreco();
    }
}
