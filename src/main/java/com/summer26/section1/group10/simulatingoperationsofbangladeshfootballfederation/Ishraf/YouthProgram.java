package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

public class YouthProgram {
    private String programID;
    private String programName;
    private String age;
    private String gender;
    private String skill;

    public YouthProgram(String programID, String programName, String age, String gender, String skill) {
        this.programID = programID;
        this.programName = programName;
        this.age = age;
        this.gender = gender;
        this.skill = skill;
    }

    public String getProgramID() {
        return programID;
    }

    public String getProgramName() {
        return programName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSkill() {
        return skill;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "YouthProgram{" +
                "programID='" + programID + '\'' +
                ", programName='" + programName + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
