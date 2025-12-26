package com.edu.gamestock.web;

import com.edu.gamestock.web.model.Funcionario;
import com.edu.gamestock.web.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginRepositoryTest {

    @Autowired
    private FuncionarioRepository repository;

    @Test
    void naoDeveAutenticarComCredenciaisInvalidas() {
        Funcionario funcionario =
                repository.findByUsuarioAndSenha("", "");

        assertNull(funcionario);
    }
}

