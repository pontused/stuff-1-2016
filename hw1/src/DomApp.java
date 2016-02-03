import dataContainers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by peter on 1/14/16.
 */
public class DomApp {

    private ArrayList<Person> employmentRecords,educationRecords;
    private ArrayList<CompanyInfo> companyRecords;

    public static void main(String[] args){
        DomApp domApp = new DomApp();
    }
    public DomApp(){
        employmentRecords = new ArrayList<Person>();
        educationRecords = new ArrayList<Person>();
        companyRecords = new ArrayList<CompanyInfo>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(true);

        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");

        // XSL Transformation
        XslTransformer xt = new XslTransformer();
        xt.start();

        // DOM parsing
        educationRecords = parseEducationRecords(factory, "src/xml/transcript.xml" , "schemas/persontranscript.xsd");

        // SAX parsing
        //companyObjects;
        companyRecords = parseCompanyRecords("src/xml/company.xml");

        employmentRecords = parseEmploymentRecords(factory, "src/xml/employment.xml", "schemas/personemployments.xsd");

        // JAXB parsing
        JAXBParser jaxbp = new JAXBParser();
        jaxbp.start();



        Person cv = parseCV(factory);



        Document output = buildApplicantProfile(factory,cv,employmentRecords,educationRecords,companyRecords);

        writeApplicantProfile(output, "src/xml/applicantProfile-DOM.xml");
    }
    private void writeApplicantProfile(Document document, String fileName){


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //get transformer to fill XML output file
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DomApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        //generate DOM tree source from the output document
        DOMSource source = new DOMSource(document);
        //get stream to fill the output file
        StreamResult result = new StreamResult(new File(fileName));
        //fill the XML output file using the stream with the DOM tree
        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(DomApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private Document buildApplicantProfile(DocumentBuilderFactory factory,Person cv,ArrayList<Person> employments,ArrayList<Person> educations, ArrayList<CompanyInfo> companyRecords){

        for (Person p : employments){
            if (p.getSSN().equals(cv.getSSN())){
                cv.addWorkhistory(p.getWorkhistory());
            }
        }
        for (Person p : educations){
            if (p.getSSN().equals(cv.getSSN())){
                cv.addEducationList(p.getEducationList());
            }
        }
        Document document = null;
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            Element rootElement = document.createElement("applicatprofile");
            document.appendChild(rootElement);

            Element firstname = document.createElement("firstname");
            firstname.appendChild(document.createTextNode(cv.getFirstname()));
            rootElement.appendChild(firstname);

            Element lastname = document.createElement("lastname");
            lastname.appendChild(document.createTextNode(cv.getLastname()));
            rootElement.appendChild(lastname);

            Element ssn = document.createElement("ssn");
            ssn.appendChild(document.createTextNode(cv.getSSN()));
            rootElement.appendChild(ssn);

            for (EducationObject eo : cv.getEducationList()){
                Element program = document.createElement("program");

                Element programName = document.createElement("programName");
                programName.appendChild(document.createTextNode(eo.getProgramName()));
                program.appendChild(programName);

                Element universityName = document.createElement("universityName");
                universityName.appendChild(document.createTextNode(eo.getUniversityName()));
                program.appendChild(universityName);


                for (CourseItem courseItem: eo.getCourseList()){
                    Element course = document.createElement("course");

                    Element courseName = document.createElement("courseName");
                    courseName.appendChild(document.createTextNode(courseItem.getCourseName()));
                    course.appendChild(courseName);

                    Element courseNumber = document.createElement("courseNumber");
                    courseNumber.appendChild(document.createTextNode(courseItem.getCourseNumber()));
                    course.appendChild(courseNumber);

                    Element degree = document.createElement("degree");
                    degree.appendChild(document.createTextNode(courseItem.getDegree()));
                    course.appendChild(degree);

                    Element startDate = document.createElement("startDate");
                    startDate.appendChild(document.createTextNode(courseItem.getStartDate()));
                    course.appendChild(startDate);

                    Element finishedDate = document.createElement("finishedDate");
                    finishedDate.appendChild(document.createTextNode(courseItem.getFinishedDate()));
                    course.appendChild(finishedDate);

                    program.appendChild(course);
                }
                rootElement.appendChild(program);
            }
            for (EmploymentObject eo : cv.getWorkhistory()) {
                Element workhistory = document.createElement("workhistory");

                Element companyName = document.createElement("companyName");
                companyName.appendChild(document.createTextNode(eo.getCompanyName()));
                workhistory.appendChild(companyName);

                Element orgNumber = document.createElement("orgNumber");
                orgNumber.appendChild(document.createTextNode(eo.getOrgNumber()));
                workhistory.appendChild(orgNumber);

                Element employmentRole = document.createElement("employmentRole");
                employmentRole.appendChild(document.createTextNode(eo.getEmploymentRole()));
                workhistory.appendChild(employmentRole);


                Element startDate = document.createElement("startDate");
                startDate.appendChild(document.createTextNode(eo.getStartDate()));
                workhistory.appendChild(startDate);

                Element endDate = document.createElement("endDate");
                endDate.appendChild(document.createTextNode(eo.getEndDate()));
                workhistory.appendChild(endDate);

                rootElement.appendChild(workhistory);
            }




        }catch (ParserConfigurationException e){

        }





        return document;
    }

    private Person parseCV(DocumentBuilderFactory factory){
        Person person = new Person();

        factory.setAttribute( "http://java.sun.com/xml/jaxp/properties/schemaSource", "schemas/shortcv.xsd");
        try{
            //Get a DocumentBuilder (parser) object
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Parse the XML input file to create a document object that represent the input XML file.
            Document document = builder.parse(new File("src/xml/shortcv.xml"));
            String prefix = document.getFirstChild().getPrefix();
            if (!prefix.equals(""))
                prefix += ":";

            NodeList cv = document.getElementsByTagName(prefix+"cv");

            if (cv.getLength() == 1){
                Element root = (Element) cv.item(0);
                person.setFirstname(root.getElementsByTagName(prefix+"firstname").item(0).getFirstChild().getNodeValue());
                person.setLastname(root.getElementsByTagName(prefix+"lastname").item(0).getFirstChild().getNodeValue());
                person.setSSN(root.getElementsByTagName(prefix+"ssn").item(0).getFirstChild().getNodeValue());
            }
        }catch (ParserConfigurationException e){

        }catch (IOException e){

        }catch (SAXException e){

        }
        return person;
    }
    private ArrayList<Person> parseEducationRecords(DocumentBuilderFactory factory, String xmlFile, String schema) {

        factory.setAttribute( "http://java.sun.com/xml/jaxp/properties/schemaSource", schema);
        ArrayList<Person> records = new ArrayList<Person>();

        try{
            //Get a DocumentBuilder (parser) object
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Parse the XML input file to create a document object that represent the input XML file.
            Document document = builder.parse(new File(xmlFile));
            String prefix = document.getFirstChild().getPrefix();
            if (!prefix.equals(""))
                prefix += ":";
            NodeList persons = document.getElementsByTagName(prefix+"person");

            for (int i = 0; i<persons.getLength();i++){
                Element person = (Element) persons.item(i);
                Person p = new Person();
                p.setFirstname(person.getElementsByTagName(prefix+"firstname").item(0).getFirstChild().getNodeValue());
                p.setLastname(person.getElementsByTagName(prefix+"lastname").item(0).getFirstChild().getNodeValue());
                p.setSSN(person.getElementsByTagName(prefix+"ssn").item(0).getFirstChild().getNodeValue());
                NodeList education = person.getElementsByTagName(prefix+"program");
                ArrayList<EducationObject> educationHistory = new ArrayList<EducationObject>();
                for (int y = 0;y<education.getLength(); y++){
                    Element educationItem = (Element) education.item(y);
                    EducationObject educationObject = new EducationObject();
                    educationObject.setProgramName(educationItem.getElementsByTagName(prefix+"programName").item(0).getFirstChild().getNodeValue());
                    educationObject.setUniversityName(educationItem.getElementsByTagName(prefix+"universityName").item(0).getFirstChild().getNodeValue());

                    ArrayList<CourseItem> courseList = new ArrayList<CourseItem>();
                    NodeList courses = educationItem.getElementsByTagName(prefix+"course");
                    for (int x= 0; x<courses.getLength();x++){
                        Element course = (Element) courses.item(x);
                        CourseItem courseItem = new CourseItem();
                        courseItem.setCourseName(course.getElementsByTagName(prefix+"courseName").item(0).getFirstChild().getNodeValue());
                        courseItem.setCourseNumber(course.getElementsByTagName(prefix+"courseNumber").item(0).getFirstChild().getNodeValue());
                        courseItem.setDegree(course.getElementsByTagName(prefix+"degree").item(0).getFirstChild().getNodeValue());
                        courseItem.setStartDate(course.getElementsByTagName(prefix+"startDate").item(0).getFirstChild().getNodeValue());
                        courseItem.setFinishedDate(course.getElementsByTagName(prefix+"finishedDate").item(0).getFirstChild().getNodeValue());
                        courseList.add(courseItem);
                    }
                    educationObject.setCourseList(courseList);
                    educationHistory.add(educationObject);
                    p.addEducationList(educationHistory);
                }
                //p.addWorkhistory(history);
                records.add(p);
            }

        }catch (ParserConfigurationException e){

        }catch (IOException e){

        }catch (SAXException e){

        }
        return records;
    }

    private ArrayList<CompanyInfo> parseCompanyRecords(String fileName){
        ArrayList<CompanyInfo> companyInfos = new ArrayList<CompanyInfo>();

        try {
            File inputFile = new File(fileName);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CompanyInfoContainer container = new CompanyInfoContainer();
            EducationHandler educationHandler = new EducationHandler(container);
            saxParser.parse(inputFile, educationHandler);
            companyInfos = container.getCompanies();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return companyInfos;
    }
    private ArrayList<Person> parseEmploymentRecords(DocumentBuilderFactory factory, String xmlFile, String schema){

        factory.setAttribute( "http://java.sun.com/xml/jaxp/properties/schemaSource", schema);
        ArrayList<Person> records = new ArrayList<Person>();

        try{
            //Get a DocumentBuilder (parser) object
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Parse the XML input file to create a document object that represent the input XML file.
            Document document = builder.parse(new File(xmlFile));
            String prefix = document.getFirstChild().getPrefix();
            if (!prefix.equals(""))
                prefix += ":";
            NodeList persons = document.getElementsByTagName(prefix+"person");

            for (int i = 0; i<persons.getLength();i++){
                Element person = (Element) persons.item(i);
                Person p = new Person();
                p.setFirstname(person.getElementsByTagName(prefix+"firstname").item(0).getFirstChild().getNodeValue());
                p.setLastname(person.getElementsByTagName(prefix+"lastname").item(0).getFirstChild().getNodeValue());
                p.setSSN(person.getElementsByTagName(prefix+"ssn").item(0).getFirstChild().getNodeValue());
                NodeList historyList = person.getElementsByTagName(prefix+"workhistory");
                ArrayList<EmploymentObject> history = new ArrayList<EmploymentObject>();
                for (int y = 0;y<historyList.getLength(); y++){
                    Element workhistory = (Element) historyList.item(y);
                    EmploymentObject employmentObject = new EmploymentObject();
                    employmentObject.setCompanyName(workhistory.getElementsByTagName(prefix+"companyName").item(0).getFirstChild().getNodeValue());
                    employmentObject.setOrgNumber(workhistory.getElementsByTagName(prefix+"orgNumber").item(0).getFirstChild().getNodeValue());
                    employmentObject.setEmploymentRole(workhistory.getElementsByTagName(prefix+"employmentRole").item(0).getFirstChild().getNodeValue());
                    employmentObject.setStartDate(workhistory.getElementsByTagName(prefix+"startDate").item(0).getFirstChild().getNodeValue());
                    employmentObject.setEndDate(workhistory.getElementsByTagName(prefix+"endDate").item(0).getFirstChild().getNodeValue());
                    history.add(employmentObject);
                }
                p.addWorkhistory(history);
                records.add(p);
            }

        }catch (ParserConfigurationException e){

        }catch (IOException e){

        }catch (SAXException e){

        }
        return records;
    }
    private void getChildObject(NodeList nodes){
        for (int i = 0; i<nodes.getLength();i++){
            System.out.println(nodes.item(i).getLocalName()+" "+ nodes.item(i).getChildNodes().item(0).getNodeValue());
        }
    }

}
