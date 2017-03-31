package com.responses;

import java.math.BigInteger;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.examples.wsdl.personservice.Documents;
import com.examples.wsdl.personservice.Person;


@WebService(endpointInterface = "pacote.mock.IResponsePerson", name = "ServiceResponsePerson")
public class ServiceResponsePerson implements IResponsePerson {

	@Override
	public SOAPMessage getResponsePerson() throws Exception {
		// TODO Auto-generated method stub
		
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage responseMessage = messageFactory.createMessage();
		
		SOAPPart soapPart = responseMessage.getSOAPPart();
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("per", "http://www.examples.com/wsdl/PersonService.wsdl");
		
		Person person = popularPerson(BigInteger.valueOf(1), "Silvio", "Santos", BigInteger.valueOf(6000));
		Documents document = popularDocuments("123.456.789-10", "12.345.678-X");
		
		SOAPBody soapBody = envelope.getBody();
		QName bodyPerson = new QName("per:person");
		
		QName id = new QName("id");
		QName firstName = new QName("firstName");
		QName lastName = new QName("lastName");
		QName age = new QName("age");
		
		QName bodyDocuments = new QName("per:documents");
		
		QName cpf = new QName("cpf");
		QName rg = new QName("rg");
		
		SOAPBodyElement soapBodyElementPerson = soapBody.addBodyElement(bodyPerson);
		
		SOAPElement soapElementPerson = soapBodyElementPerson.addChildElement(id);
		soapElementPerson.addTextNode(String.valueOf(person.getId()));
		soapElementPerson = soapBodyElementPerson.addChildElement(firstName);
		soapElementPerson.addTextNode(person.getFirstName());
		soapElementPerson = soapBodyElementPerson.addChildElement(lastName);
		soapElementPerson.addTextNode(person.getLastName());
		soapElementPerson = soapBodyElementPerson.addChildElement(age);
		soapElementPerson.addTextNode(String.valueOf(person.getAge()));
		
		SOAPBodyElement soapBodyElementDocument = soapBody.addBodyElement(bodyDocuments);
		
		SOAPElement soapElementDocument = soapBodyElementDocument.addChildElement(cpf);
		soapElementDocument.addTextNode(document.getCpf());
		soapElementDocument = soapBodyElementDocument.addChildElement(rg);
		soapElementDocument.addTextNode(document.getRg());
		
		responseMessage.saveChanges();
		
		
		return responseMessage;
		
		
	}
	
	private Person popularPerson(BigInteger id, String firstName, String lastName, BigInteger age) {
		Person person = new Person();
		
		person.setId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAge(age);
		
		return person;
	}
	
	private Documents popularDocuments(String cpf, String rg) {
		Documents documents = new Documents();
		
		documents.setCpf(cpf);
		documents.setRg(rg);
		
		return documents;
	}
	

}
