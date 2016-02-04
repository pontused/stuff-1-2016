package dataContainers;

/**
 * Created by peter on 1/24/16.
 */
public class CourseItem {

    private String courseName,courseNumber;
    private javax.xml.datatype.XMLGregorianCalendar startDate,finishedDate;
    private java.math.BigDecimal degree;
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public java.math.BigDecimal getDegree() {
        return degree;
    }

    public void setDegree(java.math.BigDecimal degree) {
        this.degree = degree;
    }

    public javax.xml.datatype.XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(javax.xml.datatype.XMLGregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public javax.xml.datatype.XMLGregorianCalendar getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(javax.xml.datatype.XMLGregorianCalendar finishedDate) {
        this.finishedDate = finishedDate;
    }
}
