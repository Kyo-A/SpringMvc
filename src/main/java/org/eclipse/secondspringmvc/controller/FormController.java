package org.eclipse.secondspringmvc.controller;

import javax.validation.Valid;

import org.eclipse.secondspringmvc.dao.PersonneRepository;
import org.eclipse.secondspringmvc.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

	@Autowired
	private PersonneRepository personneRepository;

	@GetMapping("/personne")
	public String personneForm(Model model) {
		model.addAttribute("personne", new Personne());
		return "personneForm";
	}

//	@PostMapping("/personne")
//	public String personneSubmit(@ModelAttribute("personne") Personne personne, Model model) {
//		Personne p1 = personneRepository.save(personne);
//		model.addAttribute("personne", p1);
//		return "success";
//	}

	@PostMapping("/personne")
	public String personneSubmit(@ModelAttribute("personne") @Valid Personne personne, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "personneForm";
		}
		Personne p1 = personneRepository.save(personne);
		model.addAttribute("personne", p1);
		return "success";
	}

	@GetMapping("/form")
	public String showView(Model model) {
		model.addAttribute("sexe", "");
		model.addAttribute("message", "Hello World!");
		model.addAttribute("genre", new String[] { "homme", "femme" });
		model.addAttribute("personnes", personneRepository.findAll());
		return "formSpring";
	}

}
