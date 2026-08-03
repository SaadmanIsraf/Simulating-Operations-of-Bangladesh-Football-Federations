//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
//
//import java.io.Serial;
//import java.io.Serializable;
//
//public class MedicalOfficer extends User implements Serializable {
//
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    private int age;
//    private String specialization;
//    private String contactNumber;
//
//    public MedicalOfficer(int id,
//                          String name,
//                          String email,
//                          String password,
//                          String role,
//                          int age,
//                          String specialization,
//                          String contactNumber) {
//
//        super(id, name, email, password, role);
//
//        this.age = age;
//        this.specialization = specialization;
//        this.contactNumber = contactNumber;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public String getSpecialization() {
//        return specialization;
//    }
//
//    public String getContactNumber() {
//        return contactNumber;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setSpecialization(String specialization) {
//        this.specialization = specialization;
//    }
//
//    public void setContactNumber(String contactNumber) {
//        this.contactNumber = contactNumber;
//    }
//
//    public void changePassword(String newPassword) {
//        setPassword(newPassword);
//    }
//
//    @Override
//    public void updateUser() {
//        System.out.println("Medical Officer information updated.");
//    }
//
//    @Override
//    public String toString() {
//        return "MedicalOfficer{" +
//                "id=" + getId() +
//                ", name='" + getName() + '\'' +
//                ", email='" + getEmail() + '\'' +
//                ", role='" + getRole() + '\'' +
//                ", age=" + age +
//                ", specialization='" + specialization + '\'' +
//                ", contactNumber='" + contactNumber + '\'' +
//                '}';
//    }
//}