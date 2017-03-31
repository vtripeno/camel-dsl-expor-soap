package com.responses;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.soap.SOAPMessage;

@WebService (serviceName = "PersonService")
public interface IResponsePerson {
	
	SOAPMessage getResponsePerson() throws Exception;
	
}
