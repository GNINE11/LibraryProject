package com.libraryproject.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(length = 20)
    private String telefone;

    @Column(name = "dataCadastro", insertable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoUsuario tipo = TipoUsuario.CLIENTE;

    
    public void setID(Long id){
        this.id = id;
    }

    public Long getID(){
        return id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return senha;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getTelefone(){
        return telefone;
    }
    
    public LocalDateTime getDataCadastro(){
        return dataCadastro;
    } 

    public void setTipoUsuario(TipoUsuario tipo){
        this.tipo = tipo;
    }

    public TipoUsuario getTipo(){
        return tipo;
    }

}
