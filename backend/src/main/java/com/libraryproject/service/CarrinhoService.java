package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.libraryproject.model.Carrinho;
import com.libraryproject.model.Cliente;
import com.libraryproject.repository.CarrinhoRepository;

public class CarrinhoService {
    
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Carrinho salvar(Carrinho carrinho){
        return carrinhoRepository.save(carrinho);
    }

    public Optional<Carrinho> buscarPorId(Long id){
        return carrinhoRepository.findById(id);
    }

    public Optional<Carrinho> buscarPorCliente(Cliente cliente){
        return carrinhoRepository.findByCliente(cliente);
    }

    public List<Carrinho> listarTodos(){
        return carrinhoRepository.findAll();
    }

    public void deletarPorId(Long id){
        carrinhoRepository.deleteById(id);
    }
}
