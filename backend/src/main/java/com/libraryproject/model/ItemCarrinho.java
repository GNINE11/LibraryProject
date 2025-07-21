package com.libraryproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "itemcarrinho")
@IdClass(ItemCarrinhoPK.class)
public class ItemCarrinho {
    
    @Id
    @Column(name = "carrinho_id")
    private Long carrinhoId;

    @Id
    @Column(name = "livro_id")
    private Long livroId;
    
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "condicao", length = 5)
    private CondicaoLivro condicao;

    @Column(nullable = false)
    private Integer quantidade = 1;

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public CondicaoLivro getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoLivro condicao) {
        this.condicao = condicao;
    }
}
