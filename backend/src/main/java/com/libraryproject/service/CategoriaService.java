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

    public Optional<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizarNome(Long id, String novoNome){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (categoriaRepository.findByNome(novoNome).isPresent()) {
            throw new RuntimeException("Nome já está em uso");
        }
        
        if (!categoria.getNome().equals(novoNome)) {
            categoria.setNome(novoNome);
            return categoriaRepository.save(categoria);
        }

        categoria.setNome(novoNome);
        return categoria;
    }

    public void deletarPorId(Long id) {
        categoriaRepository.deleteById(id);
    }
}
