package org.eclipse.secondspringmvc.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.eclipse.secondspringmvc.dao.AdresseRepository;
import org.eclipse.secondspringmvc.dao.PersonneRepository;

import org.eclipse.secondspringmvc.model.Adresse;
import org.eclipse.secondspringmvc.model.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Validated
public class PersonneRestController {

	private static final Logger logger = LoggerFactory.getLogger(PersonneRestController.class);

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private AdresseRepository adresseRepository;

//	@GetMapping("/personnes")
	@RequestMapping(value = "/personnes", method = RequestMethod.GET, produces = "application/json")
	public String getPersonnes() {
		return personneRepository.findAll().toString();
	}

//	@GetMapping("/personnes/{id}")
	@ResponseBody
	@RequestMapping(value = "/personnes/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Personne> getPersonne(@PathVariable("id") long id) {
		Personne personne = (personneRepository.findById(id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne not found :: " + id));

		

//		Optional<Personne> p = personneRepository.findById(id);
//		System.out.println(p);
//		if (!p.isPresent())
//			throw new ResourceNotFoundException("NOT FOUND " + id);

		return new ResponseEntity<>(personne, HttpStatus.OK);

	}

	@PostMapping(value = "/personnes", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addPersonne(@Valid @RequestBody Personne personne, Errors errors) {

//		if (errors.hasErrors()) {
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//		}

		personneRepository.save(personne);
		return new ResponseEntity<>(personne, HttpStatus.CREATED);
	}

//	@PostMapping(value = "/personnes", consumes = "application/json",
//	        produces="application/json")
//	@ResponseBody
//	public Personne addPersonne(@RequestBody Personne personne) {
//		System.out.println(personne);
//		return personneRepository.save(personne);
//	}
//	@PostMapping("/personnes")
//	public Personne addPersonne(@RequestBody Personne personne) {
//		System.out.println(personne);
//		List<Adresse> adresses = personne.getAdresses();
//		for (Adresse adresse : adresses) {
//			Adresse adr = null;
//			if (adresse.getId() != null) {
//				adr = adresseRepository.findById(adresse.getId()).orElse(null);
//				adresses.set(adresses.indexOf(adresse), adr);
//			} else {
//				adr = adresseRepository.save(adresse);
//			}
//		}
//		return personneRepository.saveAndFlush(personne);
//	}

	@PutMapping(value = "/personnes/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Personne> updatePersonne(@PathVariable("id") long id, @RequestBody Personne personne) {
		personne.setNum(id);
		personneRepository.save(personne);
		return new ResponseEntity<Personne>(personne, HttpStatus.OK);

	}

	@DeleteMapping(value = "/personnes/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Personne> deletePersonne(@PathVariable("id") long id) {
		
		Personne personneToDelete = (personneRepository.findById(id))
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne not found :: " + id));
		
		personneRepository.delete(personneToDelete);
		return new ResponseEntity<>(personneToDelete, HttpStatus.OK);
	}

}
