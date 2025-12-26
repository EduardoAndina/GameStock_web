package com.edu.gamestock.web.controller;

import com.edu.gamestock.web.model.Jogo;
import com.edu.gamestock.web.model.Venda;
import com.edu.gamestock.web.service.JogoService;
import com.edu.gamestock.web.service.VendaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final JogoService jogoService;

    public VendaController(VendaService vendaService, JogoService jogoService) {
        this.vendaService = vendaService;
        this.jogoService = jogoService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {

        model.addAttribute("vendas", vendaService.listar());
        model.addAttribute("venda", new Venda());
        model.addAttribute("jogos", jogoService.buscarPorNome(busca));

        return "vendas";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam Long jogoId,
            @RequestParam int quantidadeVendida,
            @RequestParam double valorPago,
            @RequestParam String nomeCliente,
            @RequestParam String telefoneCliente,
            RedirectAttributes redirectAttributes
    ) {

        Jogo jogo = jogoService.buscarPorId(jogoId);

        if (quantidadeVendida <= 0) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "❌ Quantidade inválida."
            );
            return "redirect:/vendas";
        }

        if (quantidadeVendida > jogo.getQuantidade()) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "❌ Estoque insuficiente para o jogo selecionado."
            );
            return "redirect:/vendas";
        }

        vendaService.registrarVenda(
                jogoId,
                quantidadeVendida,
                valorPago,
                nomeCliente,
                telefoneCliente
        );

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "✅ Venda registrada com sucesso!"
        );

        return "redirect:/vendas";
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam Long id,
            @RequestParam double valorPago,
            @RequestParam String nomeCliente,
            @RequestParam String telefoneCliente,
            RedirectAttributes redirectAttributes
    ) {

        try {
            vendaService.editarVenda(
                    id,
                    valorPago,
                    nomeCliente,
                    telefoneCliente
            );

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "✏️ Venda atualizada com sucesso!"
            );

        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "❌ Erro ao editar venda."
            );
        }

        return "redirect:/vendas";
    }

    @PostMapping("/excluir")
    public String excluir(
            @RequestParam Long id,
            RedirectAttributes redirectAttributes
    ) {

        vendaService.excluir(id);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "🗑️ Venda excluída com sucesso!"
        );

        return "redirect:/vendas";
    }
}




