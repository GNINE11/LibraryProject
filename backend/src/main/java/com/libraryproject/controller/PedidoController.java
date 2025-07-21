package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Pedido;
import com.libraryproject.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido){
        Pedido salvo = pedidoService.salvar(pedido);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id){
        return pedidoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorCliente(@PathVariable Long clienteId){
        Cliente cliente = new Cliente();
        cliente.setID(clienteId);
        List<Pedido> pedidos = pedidoService.buscarPorCliente(cliente);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos(){
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        if (pedidoService.buscarPorId(id).isPresent()){
            pedidoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
