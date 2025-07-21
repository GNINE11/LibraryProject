package com.libraryproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libraryproject.model.CondicaoLivro;
import com.libraryproject.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByCategorias_NomeContainingIgnoreCase(String nome);
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findByPrecoBetween(Double min, Double max);
    List<Livro> findByCondicao(CondicaoLivro condicao);
}
