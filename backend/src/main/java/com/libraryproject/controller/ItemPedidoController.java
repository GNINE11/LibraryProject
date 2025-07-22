package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.ItemPedido;
import com.libraryproject.model.Pedido;
import com.libraryproject.service.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {
    
    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping
    public ResponseEntity<ItemPedido> salvar(@RequestBody ItemPedido itemPedido){
        ItemPedido salvo = itemPedidoService.salvar(itemPedido);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id){
        return itemPedidoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedido>> buscarPorPedido(@PathVariable Long pedidoId){
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        List<ItemPedido> itensPedido = itemPedidoService.buscarPorPedido(pedido);
        return ResponseEntity.ok(itensPedido);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedido>> listarTodos(){
        List<ItemPedido> itensPedido = itemPedidoService.listarTodos();
        return ResponseEntity.ok(itensPedido);
    }

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<ItemPedido> atualizarQuantidade(@PathVariable Long id, @RequestParam Integer quantidade){
        try {
            ItemPedido atualizado = itemPedidoService.atualizarQuantidade(id, quantidade);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        if (itemPedidoService.buscarPorId(id).isPresent()){
            itemPedidoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
