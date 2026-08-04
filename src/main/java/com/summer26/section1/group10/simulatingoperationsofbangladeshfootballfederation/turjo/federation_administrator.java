package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;

public class federation_administrator extends User {

    public federation_administrator(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void updateUser() {
        BinaryFileUtility.writeObjects("Administrators.bin", this);
    }
}