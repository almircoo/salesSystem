package com.academia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.academia.model.Docente;
import com.academia.service.DocenteService;

@Controller
public class DocenteController {
	@Autowired
	private DocenteService docenteService;
	
	@GetMapping("/docente")
	public String listarDocente( Model model ) {
		model.addAttribute("listaDocentes", docenteService.obtenerTodo());//obtiene lista docentes existentes
		model.addAttribute("docente", new Docente()); //instancia vacia del docente
		return "docente.html";
	}
	
	@PostMapping("/docente")
	public String guardarDocente(@ModelAttribute("docente") Docente docente) {
		docenteService.guardarDocente(docente);
		return "redirect:/docente";
	}
}
