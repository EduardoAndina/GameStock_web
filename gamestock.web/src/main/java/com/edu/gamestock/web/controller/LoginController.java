package com.edu.gamestock.web.controller;

import com.edu.gamestock.web.model.Funcionario;
import com.edu.gamestock.web.repository.FuncionarioRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final FuncionarioRepository repository;

    public LoginController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(
            @RequestParam String usuario,
            @RequestParam String senha,
            HttpSession session,
            Model model
    ) {
        Funcionario funcionario =
                repository.findByUsuarioAndSenha(usuario, senha);

        if (funcionario == null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }

        session.setAttribute("usuarioLogado", funcionario);
        return "redirect:/jogos";
    }

    @PostMapping("/cadastro")
    public String cadastrarViaLogin(
            @Valid Funcionario funcionario,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "redirect:/login";
        }

        repository.save(funcionario);
        return "redirect:/login?sucesso";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}



