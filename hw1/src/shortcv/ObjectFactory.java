//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.12-b141219.1637 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.02 at 11:04:23 PM CET 
//


package com.shortcv;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shortcv package. 
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

    private final static QName _Shortcv_QNAME = new QName("http://www.shortcv.com", "shortcv");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shortcv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Cv }
     * 
     */
    public Cv createCv() {
        return new Cv();
    }

    /**
     * Create an instance of {@link CvInfo }
     * 
     */
    public CvInfo createCvInfo() {
        return new CvInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cv }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.shortcv.com", name = "shortcv")
    public JAXBElement<Cv> createShortcv(Cv value) {
        return new JAXBElement<Cv>(_Shortcv_QNAME, Cv.class, null, value);
    }

}
