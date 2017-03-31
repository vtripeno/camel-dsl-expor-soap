package com.routes;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;

import com.examples.wsdl.personservice_wsdl.PersonPortType;
import com.responses.ServiceResponsePerson;


public class MyRouteWebService extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		CamelContext camelContext = getContext();
		
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		cxfEndpoint.setAddress("http://localhost:9001/MyWebService");
		cxfEndpoint.setWsdlURL("../jboss_redhat_workspaces/expor-soap/wsdl/person.wsdl");
		cxfEndpoint.setServiceClass(PersonPortType.class.getName());
		cxfEndpoint.setCamelContext(camelContext);
		cxfEndpoint.setDataFormat(DataFormat.PAYLOAD);
		
		camelContext.addEndpoint("cxfSoap", cxfEndpoint);
		
		
		from(String.format("cxfSoap"))
			.routeId("cxfRoute")
			.log("${header.operationName}")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					exchange.getOut().setHeader("operacao", exchange.getIn().getHeader(Exchange.HTTP_PATH, String.class));
				}
			})
			.bean(new ServiceResponsePerson(), "getResponsePerson")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					// TODO Auto-generated method stub
					exchange.getOut().setBody(exchange.getIn().getBody());
					System.out.println(exchange.getOut().getBody(String.class));
				}
			})
			.transform(simple("${body}"))
		.end();
		
		
	}

}
