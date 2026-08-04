package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation;

import java.io.Serial;
import java.io.Serializable;

public abstract class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String name;
    protected String password;
    protected String role;

    public User(int id,
                String name,
                String email,
                String password,
                String role) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String password, String role) {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public final boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public abstract void updateUser();
}