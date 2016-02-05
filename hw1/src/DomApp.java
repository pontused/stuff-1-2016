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
/*
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setIgnoringElementContentWhitespace(true);

        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");
*/

    }
    public ArrayList<Person> parseEducationRecords(DocumentBuilderFactory factory, String xmlFile, String schema) {

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
                        courseItem.setDegree( new java.math.BigDecimal(course.getElementsByTagName(prefix+"degree").item(0).getFirstChild().getNodeValue()));
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

    public ArrayList<Person> parseEmploymentRecords(DocumentBuilderFactory factory, String xmlFile, String schema){

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

}
