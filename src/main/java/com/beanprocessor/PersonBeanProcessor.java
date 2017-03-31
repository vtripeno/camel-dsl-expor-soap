package com.beanprocessor;

import java.math.BigInteger;
import java.util.HashMap;

import com.examples.wsdl.personservice.Documents;
import com.examples.wsdl.personservice.Person;
import com.responses.PersonResponse;

public class PersonBeanProcessor {

	private HashMap<String, PersonResponse> map = new HashMap<>();
	private Person person;
	private Documents documents;

	public PersonBeanProcessor() {
		person = new Person();
		documents = new Documents();
		
		person.setId(BigInteger.valueOf(1));
		person.setFirstName("Silvio");
		person.setLastName("Santos");
		person.setAge(BigInteger.valueOf(6000));

		documents.setCpf("123.456.789X10");
		documents.setRg("12.345.678-X");

		PersonResponse personResponse = new PersonResponse(person, documents);

		map.put("1", personResponse);

	}

	public PersonResponse findPerson(String cId) throws Exception {

		return map.get("1");

	}

	public String deletePerson(String cId) throws Exception {

		Object obj = map.get(cId);
		if (obj == null) {
			return "not Found!";
		} else {
			map.remove(cId);
			return "Deleted!";
		}

	}

}
