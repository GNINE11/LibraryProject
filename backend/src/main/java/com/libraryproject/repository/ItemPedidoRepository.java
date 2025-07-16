package com.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.ItemPedido;
import com.libraryproject.model.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
    
    List<ItemPedido> findByPedido(Pedido pedido);
}
