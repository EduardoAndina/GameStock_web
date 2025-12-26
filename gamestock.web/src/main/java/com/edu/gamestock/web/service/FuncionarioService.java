package com.edu.gamestock.web.service;

import com.edu.gamestock.web.model.Funcionario;
import com.edu.gamestock.web.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return repository.findAll();
    }

    public List<Funcionario> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return listar();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

