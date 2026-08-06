package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;

import java.io.Serial;
import java.io.Serializable;

public class federation_administrator extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public federation_administrator(int id,
                                    String name,
                                    String password,
                                    String role) {

        super(id, name, password, role);
    }

    @Override
    public void updateUser() {
        System.out.println("Federation Administrator information updated.");
    }

    @Override
    public String toString() {
        return "federation_administrator{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
