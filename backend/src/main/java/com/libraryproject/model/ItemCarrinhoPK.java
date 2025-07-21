package com.libraryproject.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemCarrinhoPK implements Serializable {
    
    private Long carrinhoId;
    private Long livroId;
    private CondicaoLivro condicao;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(!(o instanceof ItemCarrinhoPK))
            return false;
        
        ItemCarrinhoPK that = (ItemCarrinhoPK) o;

        return Objects.equals(carrinhoId, that.carrinhoId) &&
               Objects.equals(livroId, that.livroId) &&
               condicao == that.condicao;
    }

    @Override
    public int hashCode(){
        return Objects.hash(carrinhoId, livroId, condicao);
    }

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

    public CondicaoLivro getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoLivro condicao) {
        this.condicao = condicao;
    }
}
