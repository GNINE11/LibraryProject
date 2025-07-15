package com.libraryproject.model;

import java.io.Serializable;
import java.util.Objects;

public class ItemCarrinhoPK implements Serializable {
    
    private Long carrinhoID;
    private Long livroID;
    private CondicaoLivro condicao;

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(!(o instanceof ItemCarrinhoPK))
            return false;
        
        ItemCarrinhoPK that = (ItemCarrinhoPK) o;

        return Objects.equals(carrinhoID, that.carrinhoID) &&
               Objects.equals(livroID, that.livroID) &&
               condicao == that.condicao;
    }

    @Override
    public int hashCode(){
        return Objects.hash(carrinhoID, livroID, condicao);
    }
}
