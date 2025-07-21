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

    public Cliente atualizar(Long id, Cliente novoCliente){
        return clienteRepository.findById(id)
            .map(clienteExistente -> {
                clienteExistente.setNome(novoCliente.getNome());
                clienteExistente.setTelefone(novoCliente.getTelefone());

                return clienteRepository.save(clienteExistente);
            }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public Cliente atualizarEmail(Long id, String novoEmail) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        if (clienteRepository.findByEmail(novoEmail).isPresent()) {
            throw new RuntimeException("Email já está em uso");
        }
        
        if (!cliente.getEmail().equals(novoEmail)) {
            cliente.setEmail(novoEmail);
            return clienteRepository.save(cliente);
        }
        
        return cliente;
    }

    public void atualizarSenha(Long id, String novaSenha) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        //Criptografar dps
        cliente.setSenha(novaSenha);
        clienteRepository.save(cliente);
    }

    public void deletarPorId(Long id){
        clienteRepository.deleteById(id);
    }
}
