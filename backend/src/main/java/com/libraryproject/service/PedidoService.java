package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Pedido;
import com.libraryproject.repository.PedidoRepository;

public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id){
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorCliente(Cliente cliente){
        return pedidoRepository.findByCliente(cliente);
    }

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    public void deletarPorId(Long id){
        pedidoRepository.deleteById(id);
    }
}
