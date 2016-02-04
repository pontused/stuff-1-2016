package dataContainers;

/**
 * Created by peter on 1/24/16.
 */
public class EmploymentObject {
    private String companyName,orgNumber,employmentRole;
    private javax.xml.datatype.XMLGregorianCalendar startDate,endDate;
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber) {
        this.orgNumber = orgNumber;
    }

    public String getEmploymentRole() {
        return employmentRole;
    }

    public void setEmploymentRole(String employmentRole) {
        this.employmentRole = employmentRole;
    }

    public javax.xml.datatype.XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(javax.xml.datatype.XMLGregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public javax.xml.datatype.XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(javax.xml.datatype.XMLGregorianCalendar endDate) {
        this.endDate = endDate;
    }
}
