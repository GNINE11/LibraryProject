package com.libraryproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ItemCarrinho")
@IdClass(ItemCarrinhoPK.class)
public class ItemCarrinho {
    
    @Id
    @Column(name = "carrinho_id")
    private Long carrinhoID;

    @Id
    @Column(name = "livro_id")
    private Long livroID;
    
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "condicao", length = 5)
    private CondicaoLivro condicao;

    @Column(nullable = false)
    private Integer quantidade = 1;


    public Long getCarrinhoID() {
        return carrinhoID;
    }

    public void setCarrinhoID(Long carrinhoID) {
        this.carrinhoID = carrinhoID;
    }

    public Long getLivroID() {
        return livroID;
    }

    public void setLivroID(Long livroID) {
        this.livroID = livroID;
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
