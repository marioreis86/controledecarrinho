package com.varejista.controledecarrinho.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "carrinho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean finalizado = false;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreatedDate()
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAtualizacao;

    /*@EmbeddedId
    @JsonIgnore
    private UsuarioCarrinhoPK usuarioCarrinhoPK;*/

    @JsonManagedReference
    @OneToMany
    private List<Item> itens;

    @OneToMany
    @JoinColumn(name = "cupom_id")
    private List<Cupom> cupons;

    public Carrinho(Integer id) {
        this.id = id;
        this.finalizado = false;
    }

    public Carrinho(Integer id, List<Item> itens, List<Cupom> cupons) {
        this.id = id;
        this.itens = itens;
        this.cupons = cupons;
        this.finalizado = false;
    }

    @Transient
    public Integer getNumeroProdutos() {
        return itens.size();
    }

    @Transient
    public Item getItemPorNomeProduto(String nomeProduto) {
        return itens.stream().filter(_item -> _item.getProduto().getNome().equalsIgnoreCase(nomeProduto))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Item \""+nomeProduto+"\" não encontrado"));
    }

    @Transient
    public Double getSubTotal(Item item) {
        return itens.stream().filter(_item ->_item.getProduto().getId() == item.getProduto().getId())
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Item \""+item.getProduto().getNome()+"\" não encontrado"))
                .getSubTotal();
    }

    @Transient
    public Double getSubTotalComDesconto(Item item) {
        Item found = itens.stream().filter(_item -> _item.getProduto().getId() == item.getProduto().getId())
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Item \"" + item.getProduto().getNome() + "\" não encontrado"));

        return found.getQuantidade() >= 10
                ? (found.getSubTotal() - (found.getSubTotal() * 0.10))
                : found.getSubTotal();
    }

    @Transient
    public Double getTotal() {
        return itens.stream().mapToDouble(Item::getSubTotal).sum();
    }

    @Transient
    public Double getTotalComDesconto() {
        Double totalComDesconto = itens.stream().mapToDouble(item ->
                item.getQuantidade() >= 10 ? (item.getSubTotal() - (item.getSubTotal() * 0.10)) : item.getSubTotal()
        ).sum();

        if (totalComDesconto >= 10_000)
            totalComDesconto -= totalComDesconto * 0.10;
        else if (totalComDesconto >= 5_000)
            totalComDesconto -= totalComDesconto * 0.07;
        else if (totalComDesconto >= 1_000)
            totalComDesconto -= totalComDesconto * 0.05;

        totalComDesconto = aplicarCupomDesconto(totalComDesconto);

        return totalComDesconto;
    }

    @Transient
    public Double aplicarCupomDesconto(Double total) {
        Cupom cupomMaiorDesconto = cupons.stream()
                .max(Comparator.comparingDouble(Cupom::getDesconto)).get();

        return total - (total * cupomMaiorDesconto.getDesconto());
    }
}
