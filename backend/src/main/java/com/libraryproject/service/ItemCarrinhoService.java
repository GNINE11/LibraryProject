package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.ItemCarrinho;
import com.libraryproject.model.ItemCarrinhoPK;
import com.libraryproject.repository.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {
    
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    public ItemCarrinho salvar(ItemCarrinho item) {
        return itemCarrinhoRepository.save(item);
    }

    public Optional<ItemCarrinho> buscarPorId(ItemCarrinhoPK id) {
        return itemCarrinhoRepository.findById(id);
    }

    public List<ItemCarrinho> buscarPorCarrinhoID(Long id) {
        return itemCarrinhoRepository.findByCarrinhoId(id);
    }

    public List<ItemCarrinho> listarTodos() {
        return itemCarrinhoRepository.findAll();
    }

    public ItemCarrinho atualizarQuantidade(ItemCarrinhoPK id, Integer quantidade){
        ItemCarrinho itemCarrinho = itemCarrinhoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ItemCarrinho não encontrado"));
        
        itemCarrinho.setQuantidade(quantidade);
        return itemCarrinhoRepository.save(itemCarrinho);
    }

    public void deletarPorId(ItemCarrinhoPK id) {
        itemCarrinhoRepository.deleteById(id);
    }
}
