package com.rakib.test.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rakib.test.model.Person;

public interface PersonDAO {

	int insertPerson(UUID id, Person person);
	
	default int insertPerson(Person person) {
		UUID uuid = UUID.randomUUID();
		return insertPerson(uuid, person);
	}
	
	List<Person> selectAllPerson();
	
	Optional<Person> selectPersonByID(UUID id);
	
	int deletePersonByID(UUID id);
	
	int updatePersonByID(UUID id, Person person);
}
