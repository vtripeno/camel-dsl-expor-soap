package com.responses;

import com.examples.wsdl.personservice.Documents;
import com.examples.wsdl.personservice.Person;

public class PersonResponse {
	
	private Person person;
	private Documents documents;
	
	public PersonResponse(Person person, Documents documents) {
		this.person = person;
		this.documents = documents;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Documents getDocuments() {
		return documents;
	}
	public void setDocuments(Documents documents) {
		this.documents = documents;
	}
	
	
	
}
