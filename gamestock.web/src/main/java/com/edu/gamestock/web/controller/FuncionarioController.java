package com.edu.gamestock.web.controller;

import com.edu.gamestock.web.model.Funcionario;
import com.edu.gamestock.web.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        List<Funcionario> funcionarios = service.buscarPorNome(busca);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarios";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("funcionarios", service.listar());
            return "funcionarios";
        }
        service.salvar(funcionario);
        return "redirect:/funcionarios";
    }

    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("funcionarios", service.listar());
            return "funcionarios";
        }
        service.salvar(funcionario);
        return "redirect:/funcionarios";
    }

    @PostMapping("/excluir")
    public String excluir(@RequestParam Long id) {
        service.excluir(id);
        return "redirect:/funcionarios";
             
    }
 
}

