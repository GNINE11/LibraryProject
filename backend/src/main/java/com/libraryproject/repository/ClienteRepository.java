package com.libraryproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    Optional<Cliente> findByEmail(String email);
}
