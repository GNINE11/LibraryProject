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
import com.libraryproject.model.Endereco;
import com.libraryproject.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco){
        Endereco salvo = enderecoService.salvar(endereco);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id){
        return enderecoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Endereco>> buscarPorCliente(@PathVariable Long clienteId){
        Cliente cliente = new Cliente();
        cliente.setID(clienteId);
        List<Endereco> enderecos = enderecoService.buscarPorCliente(cliente);
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos(){
        List<Endereco> enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (enderecoService.buscarPorId(id).isPresent()){
            enderecoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
}

