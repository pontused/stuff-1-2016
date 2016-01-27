package dataContainers;

import java.util.ArrayList;

/**
 * Created by peter on 1/24/16.
 */
public class Person {

    private String firstname, lastname,ssn;
    private ArrayList<EmploymentObject> workhistory;
    private ArrayList<EducationObject> educationList;


    public Person(){
        workhistory = new ArrayList<EmploymentObject>();
        educationList = new ArrayList<EducationObject>();
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public ArrayList<EmploymentObject> getWorkhistory() {
        return workhistory;
    }

    public void addWorkhistory(ArrayList<EmploymentObject> workhistory) {
        for (EmploymentObject employmentObject : workhistory)
            this.workhistory.add(employmentObject);
    }
    public void addEducationList(ArrayList<EducationObject> educationList){
        for(EducationObject educationObject : educationList)
            this.educationList.add(educationObject);
    }
    public ArrayList<EducationObject> getEducationList(){
        return educationList;
    }

    public String toString(){
        return firstname + " "+lastname + " "+ssn;
    }
}
