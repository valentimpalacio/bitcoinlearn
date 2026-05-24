package com.bitcoinlearn.controller;

import com.bitcoinlearn.repository.GlossaryTermRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/glossario")
public class GlossarioController {

    private final GlossaryTermRepository repo;

    public GlossarioController(GlossaryTermRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String glossario(Model model, @RequestParam(required = false) String categoria) {
        if (categoria != null) {
            model.addAttribute("termos", repo.findByCategory(categoria));
        } else {
            model.addAttribute("termos", repo.findAll());
        }
        model.addAttribute("categorias", repo.findAll().stream().map(t -> t.getCategory()).distinct().toList());
        model.addAttribute("categoriaAtual", categoria);
        return "glossario";
    }
}
