import dataContainers.CompanyInfo;
import dataContainers.Person;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

/**
 * Created by Pontus on 2016-02-04.
 */
public class Driver {
    public static void main(String[] args){
        Driver d = new Driver();
        d.start();
    }

    private ArrayList<Person> employmentRecords,educationRecords;
    private ArrayList<CompanyInfo> companyRecords;

    public Driver(){

    }

    public void start(){
        employmentRecords = new ArrayList<Person>();
        educationRecords = new ArrayList<Person>();
        companyRecords = new ArrayList<CompanyInfo>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(true);

        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");



        DomApp da = new DomApp();
        SaxApp sa = new SaxApp();

        // XSL Transformation
        XslTransformer xt = new XslTransformer("src/TranscriptTransformation.xsl","src/xml/Transcript.xml","src/xml/Transcript_output.xml");
        xt.start();

        // DOM parsing
        educationRecords = da.parseEducationRecords(factory, "src/xml/Transcript_output.xml", "schemas/TranscriptTransformationOutput.xsd");
        employmentRecords = da.parseEmploymentRecords(factory, "src/xml/employment.xml", "schemas/personemployments.xsd");

        // SAX parsing
        companyRecords = sa.companyInfoParser("src/xml/company.xml");

        // JAXB parsing
        JAXBParser jaxbp = new JAXBParser("src/xml/shortcv.xml");
        jaxbp.start();

        // Build XML




    }
}
