package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import java.io.Serializable;

public class MatchOfficial implements Serializable {

    private String officialId;
    private String name;
    private String role;
    private String licenseNumber;
    private String experience;

    public MatchOfficial() {
    }

    public MatchOfficial(String officialId,
                         String name,
                         String role,
                         String licenseNumber,
                         String experience) {

        this.officialId = officialId;
        this.name = name;
        this.role = role;
        this.licenseNumber = licenseNumber;
        this.experience = experience;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "MatchOfficial{" +
                "officialId='" + officialId + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
