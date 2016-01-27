package dataContainers;

import java.util.ArrayList;

/**
 * Created by peter on 1/24/16.
 */
public class EducationObject {

    private String programName,universityName;
    private ArrayList<CourseItem> courseList;

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public ArrayList<CourseItem> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<CourseItem> courseList) {
        this.courseList = courseList;
    }
}
