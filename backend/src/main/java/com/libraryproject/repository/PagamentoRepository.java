package com.libraryproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Pagamento;
import com.libraryproject.model.Pedido;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    
    Optional<Pagamento> findByPedido(Pedido pedido);
}
