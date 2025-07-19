package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.CondicaoLivro;
import com.libraryproject.model.Livro;
import com.libraryproject.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public Optional<Livro> buscarPorId(Long id){
        return livroRepository.findById(id);
    }

    public List<Livro> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorAutor(String autor){
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Livro> buscarPorCategoria(String nomeCategoria){
        return livroRepository.findByCategorias_NomeContainingIgnoreCase(nomeCategoria);
    }

    public Optional<Livro> buscarPorIsbn(String isbn){
        return livroRepository.findByIsbn(isbn);
    }

    public List<Livro> buscarPorPrecoEntre(Double min, Double max){
        return livroRepository.findByPrecoBetween(min, max);
    }

    public List<Livro> buscarPorCondicao(CondicaoLivro condicao){
        return livroRepository.findByCondicao(condicao);
    }

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

    public void deletarPorId(Long id){
        livroRepository.deleteById(id);
    }

}
