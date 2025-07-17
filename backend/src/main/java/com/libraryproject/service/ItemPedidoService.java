package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.ItemPedido;
import com.libraryproject.model.Pedido;
import com.libraryproject.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedido salvar(ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }

    public Optional<ItemPedido> buscarPorId(Long id){
        return itemPedidoRepository.findById(id);
    }

    public List<ItemPedido> buscarPorPedido(Pedido pedido){
        return itemPedidoRepository.findByPedido(pedido);
    }

    public List<ItemPedido> listarTodos(){
        return itemPedidoRepository.findAll();
    }

    public void deletarPorId(Long id){
        itemPedidoRepository.deleteById(id);
    }
}
