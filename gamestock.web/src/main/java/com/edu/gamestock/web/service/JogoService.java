package com.edu.gamestock.web.service;

import com.edu.gamestock.web.model.Jogo;
import com.edu.gamestock.web.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public List<Jogo> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return listar();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Jogo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public void diminuirEstoque(Long id, int quantidade) {
        Jogo jogo = buscarPorId(id);

        if (jogo.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        jogo.setQuantidade(jogo.getQuantidade() - quantidade);
        repository.save(jogo);
    }
}

