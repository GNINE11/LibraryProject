package com.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    List<Pedido> findByCliente(Cliente cliente);
}
