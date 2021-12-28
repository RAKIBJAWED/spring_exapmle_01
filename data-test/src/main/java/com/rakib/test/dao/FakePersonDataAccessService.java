package com.rakib.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.rakib.test.model.Person;

@Repository("fakeDAO")
public class FakePersonDataAccessService implements PersonDAO{

	public static List<Person> DB = new ArrayList<Person>();
	
	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName()));
		return 1;
	}

	@Override
	public List<Person> selectAllPerson() {
		return DB;
	}

	@Override
	public Optional<Person> selectPersonByID(UUID id) {
		return DB.stream()
				.filter(person-> person.getId().equals(id)).findFirst();
	}

	@Override
	public int deletePersonByID(UUID id) {
		Optional<Person> personMayBe = selectPersonByID(id);
		
		if(personMayBe.isEmpty()) {
			return 0;
		}
		DB.remove(personMayBe.get());
		return 1;
	}

	@Override
	public int updatePersonByID(UUID id, Person person) {
		return selectPersonByID(id).map(p -> {
			int indexOfPersontoDelete = DB.indexOf(p);
			if (indexOfPersontoDelete >= 0) {
				DB.set(indexOfPersontoDelete, person);
				return 1;
			}
			return 0;
		}).orElse(0);

	}

	
}
