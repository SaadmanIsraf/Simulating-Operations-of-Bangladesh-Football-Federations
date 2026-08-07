package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

public class Academy {
    private String academyId;
    private String academyName;
    private String academyLocation;
    private String academyType;
    private String headCoach;
    private String contactNumber;
    private String numberOfPlayers;

    public Academy(String academyId, String academyName, String academyLocation, String academyType, String headCoach, String contactNumber, String numberOfPlayers) {
        this.academyId = academyId;
        this.academyName = academyName;
        this.academyLocation = academyLocation;
        this.academyType = academyType;
        this.headCoach = headCoach;
        this.contactNumber = contactNumber;
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getAcademyId() {
        return academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public String getAcademyLocation() {
        return academyLocation;
    }

    public String getAcademyType() {
        return academyType;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public void setAcademyLocation(String academyLocation) {
        this.academyLocation = academyLocation;
    }

    public void setAcademyType(String academyType) {
        this.academyType = academyType;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academyId='" + academyId + '\'' +
                ", academyName='" + academyName + '\'' +
                ", academyLocation='" + academyLocation + '\'' +
                ", academyType='" + academyType + '\'' +
                ", headCoach='" + headCoach + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", numberOfPlayers='" + numberOfPlayers + '\'' +
                '}';
    }
}
