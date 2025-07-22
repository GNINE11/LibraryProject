package com.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.CondicaoLivro;
import com.libraryproject.model.Livro;
import com.libraryproject.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> salvar(@RequestBody Livro livro){
        Livro salvo = livroService.salvar(livro);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
        return livroService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Livro>> buscarPorTitulo(@PathVariable String titulo){
        List<Livro> livros = livroService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Livro>> buscarPorAutor(@PathVariable String autor){
        List<Livro> livros = livroService.buscarPorAutor(autor);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Livro>> buscarPorCategoria(@PathVariable String categoria){
        List<Livro> livros = livroService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("isbn/{isbn}")
    public ResponseEntity<Livro> buscarPorIsbn(@PathVariable String isbn){
        return livroService.buscarPorIsbn(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/entre")
    public ResponseEntity<List<Livro>> buscarPorPrecoEntre(@RequestParam(required = false) Double min, @RequestParam(required = false) Double max){
        List<Livro> livros = livroService.buscarPorPrecoEntre(min, max);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/condicao-livro/{condicao}")
    public ResponseEntity<List<Livro>> buscarPorCondicao(@PathVariable CondicaoLivro condicao){
        List<Livro> livros = livroService.buscarPorCondicao(condicao);
        return ResponseEntity.ok(livros);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarTodos(){
        List<Livro> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        try {
            Livro atualizado = livroService.atualizar(id, livro);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/estoque")
    public ResponseEntity<Livro> atualizarEstoque(@PathVariable Long id, @RequestParam Integer estoque){
        try{
            Livro atualizado = livroService.atualizarEstoque(id, estoque);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (livroService.buscarPorId(id).isPresent()){
            livroService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
