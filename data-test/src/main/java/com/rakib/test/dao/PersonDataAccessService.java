package com.rakib.test.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rakib.test.model.Person;

@Repository("postgre")
public class PersonDataAccessService implements PersonDAO{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		final String query = "INSERT INTO person values(?,?)";
		return jdbcTemplate.update(query, id.toString(),person.getName());
		
	}

	@Override
	public List<Person> selectAllPerson() {
		final String query = "SELECT id,name FROM person";
		
		List<Person> people = jdbcTemplate.query(query, (resultSet,i)->{
			return new Person(UUID.fromString(resultSet.getString("id")),resultSet.getString("name"));
		});
		
		
		return people;
	}

	@Override
	public Optional<Person> selectPersonByID(UUID id) {
		final String query = "SELECT id,name FROM person WHERE id=?";
		Person person = jdbcTemplate.queryForObject(query, new Object[] {id.toString()}, 
				(rs,i)->{
					return new Person(UUID.fromString(rs.getString("id")),rs.getString("name"));
				}
				);

		return Optional.ofNullable(person);
	}

	@Override
	public int deletePersonByID(UUID id) {
		final String query = "DELETE FROM person WHERE id = ?";
		return jdbcTemplate.update(query, id.toString());
		
	}

	@Override
	public int updatePersonByID(UUID id, Person person) {
		final String query = "UPDATE person SET name=? WHERE id = ?";
		return jdbcTemplate.update(query, person.getName(),id.toString());
		
	}

}
