package com.libraryproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ItemPedido")
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(nullable = false)
    private Integer quantidade = 1;

    @Column(nullable = false, precision = 10, scale = 2)
    private double precoUnitario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private CondicaoLivro condicao = CondicaoLivro.NOVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public CondicaoLivro getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoLivro condicao) {
        this.condicao = condicao;
    }
}
