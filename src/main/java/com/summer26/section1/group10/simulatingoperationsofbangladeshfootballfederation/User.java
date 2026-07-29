package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation;

public abstract class User {
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String role;


    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //getter getter to string

    public final boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    public abstract void updateUser();
}
