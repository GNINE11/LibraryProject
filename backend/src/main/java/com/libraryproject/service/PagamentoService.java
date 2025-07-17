package com.libraryproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Pagamento;
import com.libraryproject.model.Pedido;
import com.libraryproject.repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Optional<Pagamento> buscarPorPedido(Pedido pedido) {
        return pagamentoRepository.findByPedido(pedido);
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public void deletarPorId(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
