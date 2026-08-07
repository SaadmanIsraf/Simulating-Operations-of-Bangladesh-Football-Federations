package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Ishraf;

import java.io.Serializable;


    public class ClubRegistration implements Serializable {

        private static final long serialVersionUID = 1L;

        private String clubId;
        private String clubName;
        private String clubCategory;
        private String clubAddress;
        private String managerName;
        private String contactNumber;

        public ClubRegistration(String clubId, String clubName, String clubCategory, String clubAddress, String managerName, String contactNumber) {
            this.clubId = clubId;
            this.clubName = clubName;
            this.clubCategory = clubCategory;
            this.clubAddress = clubAddress;
            this.managerName = managerName;
            this.contactNumber = contactNumber;
        }

        public String getClubId() {
            return clubId;
        }

        public String getClubName() {
            return clubName;
        }

        public String getClubCategory() {
            return clubCategory;
        }

        public String getClubAddress() {
            return clubAddress;
        }

        public String getManagerName() {
            return managerName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setClubId(String clubId) {
            this.clubId = clubId;
        }

        public void setClubName(String clubName) {
            this.clubName = clubName;
        }

        public void setClubCategory(String clubCategory) {
            this.clubCategory = clubCategory;
        }

        public void setClubAddress(String clubAddress) {
            this.clubAddress = clubAddress;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        @Override
        public String toString() {
            return "ClubRegistration{" +
                    "clubId='" + clubId + '\'' +
                    ", clubName='" + clubName + '\'' +
                    ", clubCategory='" + clubCategory + '\'' +
                    ", clubAddress='" + clubAddress + '\'' +
                    ", managerName='" + managerName + '\'' +
                    ", contactNumber='" + contactNumber + '\'' +
                    '}';
        }
    }

