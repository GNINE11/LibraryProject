package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Cliente;
import com.libraryproject.model.Endereco;
import com.libraryproject.model.Pedido;
import com.libraryproject.model.StatusPedido;
import com.libraryproject.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id){
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorCliente(Cliente cliente){
        return pedidoRepository.findByCliente(cliente);
    }

    public List<Pedido> listarTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido atualizarEndereco(Long id, Endereco endereco){
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        if(pedido.getStatus() != StatusPedido.PENDENTE)
            throw new RuntimeException("Endereço só pode ser alterado quando o pedido está com o status PENDENTE");

        pedido.setEndereco(endereco);
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarStatus(Long id, StatusPedido status){
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }

    public void deletarPorId(Long id){
        pedidoRepository.deleteById(id);
    }
}
