package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.scout;

public class recommend_player {
    private String recommendationStatus;
    private String position;
    private float performanceRating;

    public recommend_player(String recommendationStatus, String position, float performanceRating) {
        this.recommendationStatus = recommendationStatus;
        this.position = position;
        this.performanceRating = performanceRating;

    }

    public String getRecommendationStatus() {
        return recommendationStatus;
    }

    public String getPosition() {
        return position;
    }

    public float getPerformanceRating() {
        return performanceRating;
    }

    public void setRecommendationStatus(String recommendationStatus) {
        this.recommendationStatus = recommendationStatus;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPerformanceRating(float performanceRating) {
        this.performanceRating = performanceRating;
    }

    @Override
    public String toString() {
        return "recommend_player{" +
                "recommendationStatus='" + recommendationStatus + '\'' +
                ", position='" + position + '\'' +
                ", performanceRating=" + performanceRating +
                '}';
    }

}
