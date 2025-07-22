package com.libraryproject.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryproject.model.Pagamento;
import com.libraryproject.model.Pedido;
import com.libraryproject.model.StatusPagamento;
import com.libraryproject.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento){
        Pagamento salvo = pagamentoService.salvar(pagamento);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id){
        return pagamentoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<Pagamento> buscarPorPedido(@PathVariable Long pedidoId){
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        Optional<Pagamento> pagamento = pagamentoService.buscarPorPedido(pedido);
        return pagamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos(){
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @PatchMapping("/{id}/data")
    public ResponseEntity<Pagamento> atualizarData(@PathVariable Long id, @RequestParam LocalDateTime data){
        try {
            Pagamento atualizado = pagamentoService.atualizarData(id, data);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusPagamento status){
        try {
            Pagamento atualizado = pagamentoService.atualizarStatus(id, status);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        if (pagamentoService.buscarPorId(id).isPresent()){
            pagamentoService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
