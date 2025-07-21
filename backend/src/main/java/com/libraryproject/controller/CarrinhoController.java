package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.libraryproject.model.Carrinho;
import com.libraryproject.model.Cliente;
import com.libraryproject.service.CarrinhoService;
import com.libraryproject.service.ClienteService;


@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {
    
    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Carrinho> salvar(@RequestBody Carrinho carrinho){
        Carrinho salvo = carrinhoService.salvar(carrinho);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> buscarPorId(@PathVariable Long id){
        return carrinhoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Carrinho> buscarPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteService.buscarPorId(clienteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return carrinhoService.buscarPorCliente(cliente).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> listarTodos(){
        List<Carrinho> carrinhos = carrinhoService.listarTodos();
        return ResponseEntity.ok(carrinhos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(carrinhoService.buscarPorId(id).isPresent()){
            carrinhoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
