
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.examples.wsdl.personservice_wsdl;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.6
 * 2017-03-31T13:18:54-03:00
 * Generated source version: 3.0.6
 * 
 */

@javax.jws.WebService(
                      serviceName = "Person_Service",
                      portName = "Person_Port",
                      targetNamespace = "http://www.examples.com/wsdl/PersonService.wsdl",
                      wsdlLocation = "file:/C:/Users/victor.tripeno/Documents/jboss_redhat_workspaces/expor-soap/wsdl/person.wsdl",
                      endpointInterface = "com.examples.wsdl.personservice_wsdl.PersonPortType")
                      
public class Person_PortImpl implements PersonPortType {

    private static final Logger LOG = Logger.getLogger(Person_PortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.examples.wsdl.personservice_wsdl.PersonPortType#person(com.examples.wsdl.personservice.Person  person )*
     */
    public com.examples.wsdl.personservice.Documents person(javax.xml.ws.Holder<com.examples.wsdl.personservice.Person> person) { 
        LOG.info("Executing operation person");
        System.out.println(person.value);
        try {
            com.examples.wsdl.personservice.Documents _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
