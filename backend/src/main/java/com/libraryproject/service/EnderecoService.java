package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Endereco;
import com.libraryproject.repository.ClienteRepository;
import com.libraryproject.repository.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Endereco salvar(Endereco endereco){
        if (endereco.getCliente() != null && endereco.getCliente().getID() != null) {
            Cliente cliente = clienteRepository.findById(endereco.getCliente().getID())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + endereco.getCliente().getID()));
            endereco.setCliente(cliente);
        }
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

    public Endereco atualizar(Long id, Endereco novoEndereco){
        return enderecoRepository.findById(id)
            .map(enderecoExistente -> {
                enderecoExistente.setRua(novoEndereco.getRua());
                enderecoExistente.setNumero(novoEndereco.getNumero());
                enderecoExistente.setComplemento(novoEndereco.getComplemento());
                enderecoExistente.setBairro(novoEndereco.getBairro());
                enderecoExistente.setCidade(novoEndereco.getCidade());
                enderecoExistente.setEstado(novoEndereco.getEstado());
                enderecoExistente.setCep(novoEndereco.getCep());

                return enderecoRepository.save(enderecoExistente);
            }).orElseThrow(() -> new RuntimeException("Rua não encontrada com o id: " + id));
    }

    public Endereco definirComoPrincipal(Long id){
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));

        Cliente cliente = endereco.getCliente();
        List<Endereco> enderecosDoCliente = enderecoRepository.findByCliente(cliente);

        for(Endereco atual : enderecosDoCliente){
            if (atual.getIsPrincipal().equals(Boolean.TRUE)){
                atual.setIsPrincipal(false);
                enderecoRepository.save(atual);
            }
        }

        endereco.setIsPrincipal(true);
        return enderecoRepository.save(endereco);
    }

    public void deletarPorId(Long id){
        enderecoRepository.deleteById(id);
    }
}
