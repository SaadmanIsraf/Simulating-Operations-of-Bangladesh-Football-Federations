package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;

import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.User;
import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;

import java.time.LocalDate;

public class federation_administrator extends User {

    public federation_administrator(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }

    @Override
    public void updateUser() {
        BinaryFileUtility.writeObjects("Administrators.bin", this);
    }


//    String matchId, String hometeam, String awayteam, String competition,
//    String stadium, LocalDate matchdate, String matchtime,
//    String officialId, String status
    public Managematch manageMatchControl(String matchId, String hometeam, String awayteam, String competition,
                                          String stadium, LocalDate matchdate, String matchtime,
                                          String officialId, String status){

        Managematch newMatch = new Managematch(matchId, hometeam, awayteam, competition,
                stadium, matchdate, matchtime,
                officialId, status);

        BinaryFileUtility.writeObjects("Managematches.bin", newMatch);
        return newMatch;
    }

}