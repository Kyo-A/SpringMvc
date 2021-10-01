package org.eclipse.secondspringmvc.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.secondspringmvc.dao.PersonneRepository;
import org.eclipse.secondspringmvc.model.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PersonneController {

	@Autowired(required=true)
	private PersonneRepository personneRepository;

	@GetMapping(value = "/addPerson")
	public String addPerson() {
		return "addPerson";
	}

	@PostMapping(value = "/addPerson")
	public ModelAndView addPerson(@RequestParam(value = "nom") String nom,
			@RequestParam(value = "prenom") String prenom) {
		Personne p1 = new Personne(nom, prenom);
		personneRepository.save(p1);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("confirm");
		mv.addObject("nom", nom);
		mv.addObject("prenom", prenom);
		return mv;

	}
	
	

	@GetMapping(value = "/showAll")
	public ModelAndView showAll() {
		List<Personne> personnes = (ArrayList<Personne>) personneRepository.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("personnes", personnes);
		return mv;
	}

	@GetMapping(value = "/showAllByPage/{i}/{j}")
	public ModelAndView showAllByPage(@PathVariable int i, @PathVariable int j) {
		Page<Personne> personnes = personneRepository.findAll(PageRequest.of(i, j));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("personnes", personnes.getContent());
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
