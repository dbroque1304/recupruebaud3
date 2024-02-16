package org.iesvdm.pruebarecuud3.controller;

import jakarta.validation.Valid;
import org.iesvdm.pruebarecuud3.domain.Departamento;
import org.iesvdm.pruebarecuud3.domain.Profesor;
import org.iesvdm.pruebarecuud3.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/profesores")
    public String listar(Model model) {

        List<Profesor> profesorList =  profesorService.listAll();
        model.addAttribute("profesorList", profesorList);

        return "profesores";

    }
    @GetMapping("/profesores/crear")
    public String crear(Model model) {

        Profesor profesor = new Profesor();
        List<Departamento> departamentoList = profesorService.getDepartamentos();
        model.addAttribute("departamentoList", departamentoList);
        model.addAttribute("profesor", profesor);

        return "crear-profesor";

    }
    @PostMapping("/profesores/crear")
    public String submitCrear(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            List<Departamento> departamentoList = profesorService.getDepartamentos();
            model.addAttribute("departamentoList", departamentoList);
            model.addAttribute("profesor", profesor);

            return "crear-profesor";
        }
        profesorService.newProfesor(profesor);

        return "redirect:/profesores";

    }
}
