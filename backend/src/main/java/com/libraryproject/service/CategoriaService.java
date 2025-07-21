package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Categoria;
import com.libraryproject.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}
