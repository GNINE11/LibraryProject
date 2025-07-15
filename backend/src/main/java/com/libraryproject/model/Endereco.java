package com.libraryproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false, length = 150)
    private String rua;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(name = "is_principal", nullable = false)
    private Boolean isPrincipal = false;


    public void setID(Long id){
        this.id = id;
    }

    public Long getID(){
        return id;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getRua(){
        return rua;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }

    public String getComplemento(){
        return complemento;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public String getBairro(){
        return bairro;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getCidade(){
        return cidade;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getEstado(){
        return estado;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public String getCep(){
        return cep;
    }

    public void setIsPrincipal(boolean isPrincipal){
        this.isPrincipal = isPrincipal;
    }

    public Boolean getIsPrincipal(){
        return isPrincipal;
    }
}
