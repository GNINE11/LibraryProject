package com.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.ItemCarrinho;
import com.libraryproject.model.ItemCarrinhoPK;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinhoPK> {

    List<ItemCarrinho> findByCarrinhoId(Long carrinhoId);
}
