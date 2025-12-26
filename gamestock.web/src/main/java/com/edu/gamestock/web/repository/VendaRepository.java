package com.edu.gamestock.web.repository;

import com.edu.gamestock.web.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByJogo_NomeContainingIgnoreCase(String nome);

    List<Venda> findByNomeClienteContainingIgnoreCase(String nomeCliente);
}
