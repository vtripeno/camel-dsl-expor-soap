package com.test;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.SOAPMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.responses.ServiceResponsePerson;

public class TestWebService extends CamelTestSupport {
	
	CxfEndpoint cxfEndpoint;
	CamelContext camelContext;
	
	private final int port1 = AvailablePortFinder.getNextAvailable();
	
	@Override
	public RouteBuilder createRouteBuilder() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				camelContext = getContext();
				
				cxfEndpoint = new CxfEndpoint();
				cxfEndpoint.setAddress("http://localhost:" + port1 + "?wsdl");
				cxfEndpoint.setWsdlURL("wsdl/person.wsdl");
				cxfEndpoint.setCamelContext(camelContext);
//				cxfEndpoint.setServiceClass(PersonPortType.class.getName());
				cxfEndpoint.setDataFormat(DataFormat.PAYLOAD);
				
				camelContext.addEndpoint("cxfSoap", cxfEndpoint);
				
				from("direct:start").to(String.format("cxfSoap")).to("mock:end");

			}
		};

	}
	
	@Test
	public void testWebService () throws Exception {
		
		MockEndpoint myMock = getMockEndpoint("mock:end");

		ServiceResponsePerson serviceResponsePerson = new ServiceResponsePerson();
		SOAPMessage soapMessage = serviceResponsePerson.getResponsePerson();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		soapMessage.writeTo(outputStream);
		String output = new String(outputStream.toByteArray());

		template.requestBody("mock:end", output);

		myMock.expectedMessageCount(1);
		assertMockEndpointsSatisfied();
		
		
	}
}
