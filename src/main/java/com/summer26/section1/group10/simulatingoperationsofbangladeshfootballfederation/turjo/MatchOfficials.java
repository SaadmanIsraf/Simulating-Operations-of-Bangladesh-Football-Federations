package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;

import java.io.Serializable;

public class MatchOfficials extends User implements Serializable {
    private final int licenseNumber;
    private String exprerienceLevel;
    private String matchOfficeRole;

    public MatchOfficials(int id, String name, String email, String password, String role, int licenseNumber, String exprerienceLevel, String matchOfficeRole) {
        super(id, name, email, password, role);
        this.licenseNumber = licenseNumber;
        this.exprerienceLevel = exprerienceLevel;
        this.matchOfficeRole = matchOfficeRole;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getExprerienceLevel() {
        return exprerienceLevel;
    }

    public void setExprerienceLevel(String exprerienceLevel) {
        this.exprerienceLevel = exprerienceLevel;
    }

    public String getMatchOfficeRole() {
        return matchOfficeRole;
    }

    public void setMatchOfficeRole(String matchOfficeRole) {
        this.matchOfficeRole = matchOfficeRole;
    }

    @Override
    public void updateUser() {
        BinaryFileUtility.writeObjects("MatchOfficials.bin", this);
    }
}