package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

public class finance_officer_profile {
    private String financialOfficerId;
    private String fullName;
    private String email;
    private String contactNumber;
    private String department;
    private String position;
    private String currentPassword,newPassword;

    public finance_officer_profile(String financialOfficerId, String fullName, String email, String contactNumber, String department, String position, String currentPassword, String newPassword) {
        this.financialOfficerId = financialOfficerId;
        this.fullName = fullName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.department = department;
        this.position = position;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getFinancialOfficerId() {
        return financialOfficerId;
    }

    public void setFinancialOfficerId(String financialOfficerId) {
        this.financialOfficerId = financialOfficerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "finance_officer_profile{" +
                "financialOfficerId='" + financialOfficerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", currentPassword='" + currentPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
