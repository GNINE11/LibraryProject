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

import com.libraryproject.model.CondicaoLivro;
import com.libraryproject.model.ItemCarrinho;
import com.libraryproject.model.ItemCarrinhoPK;
import com.libraryproject.service.ItemCarrinhoService;

@RestController
@RequestMapping("/itens-carrinho")
public class ItemCarrinhoController {
    
    @Autowired
    private ItemCarrinhoService itemCarrinhoService;

    @PostMapping
    public ResponseEntity<ItemCarrinho> salvar(@RequestBody ItemCarrinho item){
        ItemCarrinho salvo = itemCarrinhoService.salvar(item);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{carrinhoId}/{livroId}/{condicao}")
    public ResponseEntity<ItemCarrinho> buscarPorId(@PathVariable Long carrinhoId, @PathVariable Long livroId, @PathVariable CondicaoLivro condicao){
        
        ItemCarrinhoPK id = new ItemCarrinhoPK();
        id.setCarrinhoId(carrinhoId);
        id.setLivroId(livroId);
        id.setCondicao(condicao);

        return itemCarrinhoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carrinho/{carrinhoId}")
    public ResponseEntity<List<ItemCarrinho>> buscarPorCarrinhoId(@PathVariable Long carrinhoId){
        List<ItemCarrinho> itensCarrinho = itemCarrinhoService.buscarPorCarrinhoID(carrinhoId);
        return ResponseEntity.ok(itensCarrinho);
    }

    @GetMapping
    public ResponseEntity<List<ItemCarrinho>> listarTodos(){
        List <ItemCarrinho> itensCarrinho = itemCarrinhoService.listarTodos();
        return ResponseEntity.ok(itensCarrinho);
    }

    @DeleteMapping("/{carrinhoId}/{livroId}/{condicao}")
    public ResponseEntity<Void> deletarPorID(@PathVariable Long carrinhoId, @PathVariable Long livroId, @PathVariable CondicaoLivro condicao){
        
        ItemCarrinhoPK id = new ItemCarrinhoPK();
        id.setCarrinhoId(carrinhoId);
        id.setLivroId(livroId);
        id.setCondicao(condicao);

        if (itemCarrinhoService.buscarPorId(id).isPresent()){
            itemCarrinhoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
