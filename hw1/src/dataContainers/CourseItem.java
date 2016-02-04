package dataContainers;

/**
 * Created by peter on 1/24/16.
 */
public class CourseItem {

    private String courseName,courseNumber;
    private String startDate,finishedDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) { this.finishedDate = finishedDate; }
}
