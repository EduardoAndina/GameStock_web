package com.edu.gamestock.web.repository;

import com.edu.gamestock.web.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

    Funcionario findByUsuarioAndSenha(String usuario, String senha);
}

