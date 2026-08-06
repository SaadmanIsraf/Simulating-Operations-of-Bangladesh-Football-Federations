package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;

public class match_official extends User {

    public match_official(int id,
                          String name,
                          String password,
                          String role) {

        super(id, name, password, role);
    }

    @Override
    public void updateUser() {

    }

    @Override
    public String toString() {
        return "match_official{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}