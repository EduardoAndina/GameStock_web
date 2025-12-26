package com.edu.gamestock.web.service;

import com.edu.gamestock.web.model.Jogo;
import com.edu.gamestock.web.model.Venda;
import com.edu.gamestock.web.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final JogoService jogoService;

    public VendaService(VendaRepository vendaRepository, JogoService jogoService) {
        this.vendaRepository = vendaRepository;
        this.jogoService = jogoService;
    }

    public void registrarVenda(
            Long jogoId,
            int quantidade,
            double valorPago,
            String nomeCliente,
            String telefoneCliente
    ) {

        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade inválida");
        }

        Jogo jogo = jogoService.buscarPorId(jogoId);

        if (jogo.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }

        jogoService.diminuirEstoque(jogoId, quantidade);

        Venda venda = new Venda();
        venda.setJogo(jogo);
        venda.setQuantidadeVendida(quantidade);
        venda.setValorPago(valorPago);
        venda.setNomeCliente(nomeCliente);
        venda.setTelefoneCliente(telefoneCliente);

        vendaRepository.save(venda);
    }

    public void editarVenda(
            Long id,
            double valorPago,
            String nomeCliente,
            String telefoneCliente
    ) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setValorPago(valorPago);
        venda.setNomeCliente(nomeCliente);
        venda.setTelefoneCliente(telefoneCliente);

        vendaRepository.save(venda);
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public void excluir(Long id) {
        vendaRepository.deleteById(id);
    }
}




