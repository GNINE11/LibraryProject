package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Cliente;
import com.libraryproject.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorEmail(String email){
        return clienteRepository.findByEmail(email);
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public void deletarPorId(Long id){
        clienteRepository.deleteById(id);
    }
}
