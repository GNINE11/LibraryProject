package com.libraryproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Carrinho;
import com.libraryproject.model.Cliente;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByCliente(Cliente cliente);
}
