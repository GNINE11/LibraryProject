package com.libraryproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryproject.model.Pagamento;
import com.libraryproject.model.Pedido;
import com.libraryproject.model.StatusPagamento;
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

    public Pagamento atualizarData(Long id, LocalDateTime data){
        Pagamento pagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        
        pagamento.setDataPagamento(data);
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarStatus(Long id, StatusPagamento status){
        Pagamento pagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
        
        pagamento.setStatus(status);
        return pagamentoRepository.save(pagamento);
    }

    public void deletarPorId(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
