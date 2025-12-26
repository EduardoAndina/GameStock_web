package com.edu.gamestock.web.controller;

import com.edu.gamestock.web.model.Jogo;
import com.edu.gamestock.web.service.JogoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jogos")
public class JogoController {

    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        List<Jogo> jogos = service.buscarPorNome(busca);
        model.addAttribute("jogos", jogos);
        model.addAttribute("jogo", new Jogo());
        model.addAttribute("busca", busca);
        return "jogos";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Jogo jogo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("jogos", service.buscarPorNome(null));
            model.addAttribute("jogo", jogo);
            return "jogos";
        }
        service.salvar(jogo);
        return "redirect:/jogos";
    }

    @PostMapping("/editar")
    public String editar(@Valid Jogo jogo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("jogos", service.buscarPorNome(null));
            model.addAttribute("jogo", jogo);
            return "jogos";
        }
        service.salvar(jogo);
        return "redirect:/jogos";
    }

    @PostMapping("/excluir")
    public String excluir(@RequestParam Long id) {
        service.excluir(id);
        return "redirect:/jogos";
    }
}





