package com.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    
    List<Endereco> findByCliente(Cliente cliente);
}
