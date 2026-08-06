//package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.turjo;
//
//import com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Utility.BinaryFileUtility;
//
//import java.util.Random;
//
//public class RandomOfficialGenerator {
//
//    private static final String[] FIRST_NAMES = {
//            "Rakib", "Tanvir", "Shakil", "Nayeem", "Arafat",
//            "Fahim", "Ashraful", "Imran", "Rashed", "Sabbir"
//    };
//
//    private static final String[] LAST_NAMES = {
//            "Hossain", "Rahman", "Ahmed", "Karim", "Islam",
//            "Uddin", "Chowdhury", "Hasan", "Alam", "Khan"
//    };
//
//    private static final String[] ROLES = {
//            "Referee", "Assistant Referee", "Fourth Official", "VAR Official"
//    };
//
//    private static final String[] EXPERIENCE_LEVELS = {
//            "1-2 Years", "3-5 Years", "6-10 Years", "10+ Years"
//    };
//
//    public static void main(String[] args) {
//
//        int howMany = 10;
//
//        generateRandomOfficials(howMany);
//
//        System.out.println(howMany + " random match officials generated and saved to User.bin");
//    }
//
//    public static void generateRandomOfficials(int count) {
//
//        Random random = new Random();
//
//        for (int i = 0; i < count; i++) {
//
//            int id = 1000 + random.nextInt(9000);
//
//            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
//            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
//            String fullName = firstName + " " + lastName;
//
//            String email = (firstName + "." + lastName).toLowerCase() + id + "@federation.local";
//            String password = "test1234";
//            String role = "Match Officials";
//
//            int licenseNumber = 100000 + random.nextInt(900000);
//            String experienceLevel = EXPERIENCE_LEVELS[random.nextInt(EXPERIENCE_LEVELS.length)];
//            String matchOfficeRole = ROLES[random.nextInt(ROLES.length)];
//
//            MatchOfficials official = new MatchOfficials(
//                    id,
//                    fullName,
//                    email,
//                    password,
//                    role,
//                    licenseNumber,
//                    experienceLevel,
//                    matchOfficeRole);
//
//            BinaryFileUtility.writeObjects("User.bin", official);
//
//            System.out.println("Generated: ID=" + id + ", Name=" + fullName + ", Role=" + matchOfficeRole);
//        }
//    }
//}