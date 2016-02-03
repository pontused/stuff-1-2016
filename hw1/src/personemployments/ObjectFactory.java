//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.12-b141219.1637 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.02 at 11:03:45 PM CET 
//


package com.personemployments;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.personemployments package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Personemployments_QNAME = new QName("http://www.personemployments.com", "personemployments");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.personemployments
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link PersonObject }
     * 
     */
    public PersonObject createPersonObject() {
        return new PersonObject();
    }

    /**
     * Create an instance of {@link Employment }
     * 
     */
    public Employment createEmployment() {
        return new Employment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.personemployments.com", name = "personemployments")
    public JAXBElement<Person> createPersonemployments(Person value) {
        return new JAXBElement<Person>(_Personemployments_QNAME, Person.class, null, value);
    }

}
