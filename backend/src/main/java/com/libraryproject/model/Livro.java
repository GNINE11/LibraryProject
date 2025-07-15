package com.libraryproject.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Livro")
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String autor;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 50)
    private String categoria;

    @Column(length = 255)
    private String imagemURL;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double preco;

    @Column(name = "estoqueDisponivel", nullable = false)
    private Integer estoqueDisponivel = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private CondicaoLivro condicao = CondicaoLivro.NOVO;
    
    @Column(name = "dataCadastro", insertable = false, updatable = false)
    private LocalDateTime dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoqueDisponivel() {
        return estoqueDisponivel;
    }

    public void setEstoqueDisponivel(int estoqueDisponivel) {
        this.estoqueDisponivel = estoqueDisponivel;
    }

    public CondicaoLivro getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoLivro condicao) {
        this.condicao = condicao;
    }

    public LocalDateTime getDataCadastro(){
        return dataCadastro;
    } 
    

}
