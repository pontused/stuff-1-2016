import com.applicationschema.Application;
import com.applicationschema.CourseTranscript;
import com.applicationschema.Schoolprogram;
import com.applicationschema.Workhistorytype;
import dataContainers.*;

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
        String ssn; //the applicant

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
        Application ap = new Application();


        // JAXB parsing
        JAXBParser jaxbp = new JAXBParser("src/xml/shortcv.xml");
        ssn = jaxbp.start(ap);

        // XSL Transformation
        XslTransformer xt = new XslTransformer("src/TranscriptTransformation.xsl","src/xml/Transcript.xml","src/xml/Transcript_output.xml");
        xt.start();

        // DOM parsing
        educationRecords = da.parseEducationRecords(factory, "src/xml/transcript.xml", "schemas/persontranscript.xsd");
        employmentRecords = da.parseEmploymentRecords(factory, "src/xml/employment.xml", "schemas/personemployments.xsd");
        Workhistorytype wh;
        for (Person p :employmentRecords) {
            if(p.getSSN().equals(ssn)) {
                System.out.println("Found an apropriet employment");
                for (EmploymentObject eo : p.getWorkhistory()) {
                    wh = new Workhistorytype();
                    wh.setCompanyName(eo.getCompanyName());
                    wh.setOrgNumber(eo.getOrgNumber());
                    wh.setEmploymentRole(eo.getEmploymentRole());
                    wh.setStartDate(eo.getStartDate());
                    wh.setEndDate(eo.getEndDate());
                }
            }
        }
        Schoolprogram sp;
        CourseTranscript ct;
        for (Person p :educationRecords) {
            if(p.getSSN().equals(ssn)) {
                System.out.println("Found an apropriet education");
                for (EducationObject edo : p.getEducationList()) {
                    sp = new Schoolprogram();
                    sp.setProgramName(edo.getProgramName());
                    sp.setUniversityName(edo.getUniversityName());
                    for (CourseItem ci :edo.getCourseList()) {
                        ct = new CourseTranscript();
                        ct.setCourseName(ci.getCourseName());
                        ct.setCourseNumber(ci.getCourseNumber());
                        ct.setDegree(ci.getDegree());
                        ct.setStartDate(ci.getStartDate());
                        ct.setFinishedDate(ci.getFinishedDate());
                    }
                }
            }
        }
        /*
        // SAX parsing
        companyRecords = sa.companyInfoParser("src/xml/company.xml");
        */




        // Build XML




    }
}
