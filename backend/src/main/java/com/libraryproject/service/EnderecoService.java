package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Endereco;
import com.libraryproject.repository.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> buscarPorId(Long id){
        return enderecoRepository.findById(id);
    }

    public List<Endereco> buscarPorCliente(Cliente cliente){
        return enderecoRepository.findByCliente(cliente);
    }

    public List<Endereco> listarTodos(){
        return enderecoRepository.findAll();
    }

    public void deletarPorId(Long id){
        enderecoRepository.deleteById(id);
    }
}
