package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingSchedule implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int trainingId;
    private LocalDate trainingDate;
    private String trainingTime;
    private String venue;
    private String coachName;
    private ArrayList<Integer> enrolledPlayerIds;

    public TrainingSchedule() {

        enrolledPlayerIds = new ArrayList<>();
    }

    public TrainingSchedule(
            int trainingId,
            LocalDate trainingDate,
            String trainingTime,
            String venue,
            String coachName,
            ArrayList<Integer> enrolledPlayerIds) {

        this.trainingId = trainingId;
        this.trainingDate = trainingDate;
        this.trainingTime = trainingTime;
        this.venue = venue;
        this.coachName = coachName;

        if (enrolledPlayerIds == null) {

            this.enrolledPlayerIds =
                    new ArrayList<>();

        } else {

            this.enrolledPlayerIds =
                    enrolledPlayerIds;
        }
    }

    public int getTrainingId() {
        return trainingId;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public String getVenue() {
        return venue;
    }

    public String getCoachName() {
        return coachName;
    }

    public ArrayList<Integer> getEnrolledPlayerIds() {

        if (enrolledPlayerIds == null) {
            enrolledPlayerIds = new ArrayList<>();
        }

        return enrolledPlayerIds;
    }

    public String getEnrollmentCount() {

        return getEnrolledPlayerIds().size()
                + " / 11";
    }

    public String getSessionStatus() {

        if (getEnrolledPlayerIds().size() >= 11) {
            return "Full";
        }

        return "Available";
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public void setTrainingDate(
            LocalDate trainingDate) {

        this.trainingDate = trainingDate;
    }

    public void setTrainingTime(
            String trainingTime) {

        this.trainingTime = trainingTime;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCoachName(
            String coachName) {

        this.coachName = coachName;
    }

    public void setEnrolledPlayerIds(
            ArrayList<Integer> enrolledPlayerIds) {

        if (enrolledPlayerIds == null) {

            this.enrolledPlayerIds =
                    new ArrayList<>();

        } else {

            this.enrolledPlayerIds =
                    enrolledPlayerIds;
        }
    }

    @Override
    public String toString() {

        return "TrainingSchedule{" +
                "trainingId=" + trainingId +
                ", trainingDate=" + trainingDate +
                ", trainingTime='" + trainingTime + '\'' +
                ", venue='" + venue + '\'' +
                ", coachName='" + coachName + '\'' +
                ", enrolledPlayerIds=" +
                enrolledPlayerIds +
                '}';
    }
}