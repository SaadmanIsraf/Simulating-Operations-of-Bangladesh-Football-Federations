package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;
import java.io.Serializable;

public class WomenFootball {
    private String programId;
    private String programName;
    private String age;
    private String teamName;
    private String coachName;
    private String contactNumber;

    public WomenFootball(String programId, String programName, String age, String teamName, String coachName, String contactNumber) {
        this.programId = programId;
        this.programName = programName;
        this.age = age;
        this.teamName = teamName;
        this.coachName = coachName;
        this.contactNumber = contactNumber;
    }

    public String getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getAge() {
        return age;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "WomenFootball{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", age='" + age + '\'' +
                ", teamName='" + teamName + '\'' +
                ", coachName='" + coachName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
