package com.rakib.test.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rakib.test.dao.PersonDAO;
import com.rakib.test.model.Person;

@Service
public class PersonService {

	public final PersonDAO personDAO;

	@Autowired
	public PersonService(@Qualifier("postgre") PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	public int addPerson(Person person) {
		return personDAO.insertPerson(person);
	}
	
	public List<Person> getAllPeople(){
		return personDAO.selectAllPerson();
	}
	
	public Optional<Person> getPersonByID(UUID id){
		return personDAO.selectPersonByID(id);
	}
	
	public int deletePerson(UUID id) {
		return personDAO.deletePersonByID(id);
	}
	
	public int updatePerson(UUID id, Person person) {
		
		return personDAO.updatePersonByID(id, new Person(id, person.getName()));
	}
}
