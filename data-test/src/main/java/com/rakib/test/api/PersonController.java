package com.rakib.test.api;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakib.test.model.Person;
import com.rakib.test.service.PersonService;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@GetMapping
	public List<Person> getAllPeople(){
		return personService.getAllPeople();
	}
	
	@GetMapping(path = "{id}")
	public Person getPersonByID(@PathVariable("id") UUID id) {
		return personService.getPersonByID(id).orElse(null);
	}
	
	@DeleteMapping(path = "{id}")
	public int deletePerson(@PathVariable("id") UUID id) {
		return personService.deletePerson(id);
	}
	
	@PutMapping(path = "{id}")
	public int updatePerson(@PathVariable("id") UUID id,@RequestBody Person person) {
		return personService.updatePerson(id, person);
	}
}
