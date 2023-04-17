package com.example.szakdolgozatfinal;

public class CourseModel {
    // variables for our course
    // name and description.
    private String courseName;
    private String courseDescription;
    private String regulationString;
    private String licenseString;
    private String generalData;
    private String allowedInAgriculture;
    private String restrictionString;
    private String warningString;

    // creating constructor for our variables.
    public CourseModel(String courseName, String courseDescription, String regulationString, String licenseString, String generalData,
                       String allowedInAgriculture, String restrictionString, String warningString) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.regulationString = regulationString;
        this.licenseString = licenseString;
        this.generalData = generalData;
        this.allowedInAgriculture = allowedInAgriculture;
        this.restrictionString = restrictionString;
        this.warningString = warningString;
    }

    // creating getter and setter methods.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getRegulationString() {
        return regulationString;
    }

    public String getLicenseString() {
        return licenseString;
    }

    public String getGeneralData() {
        return generalData;
    }

    public String getAllowedInAgriculture() {
        return allowedInAgriculture;
    }

    public String getRestrictionString() {
        return restrictionString;
    }

    public String getWarningString() {
        return warningString;
    }
}