package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Juti.financial_officer;

public class manage_annual_budget {
    private  String budgetID ;
    private int year;
    private String category,allocated_budget,spent_budget,remaining_budget;

    public manage_annual_budget(String budgetID, int year, String category, String allocated_budget, String spent_budget, String remaining_budget) {
        this.budgetID = budgetID;
        this.year = year;
        this.category = category;
        this.allocated_budget = allocated_budget;
        this.spent_budget = spent_budget;
        this.remaining_budget = remaining_budget;
    }

    public String getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(String budgetID) {
        this.budgetID = budgetID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAllocated_budget() {
        return allocated_budget;
    }

    public void setAllocated_budget(String allocated_budget) {
        this.allocated_budget = allocated_budget;
    }

    public String getSpent_budget() {
        return spent_budget;
    }

    public void setSpent_budget(String spent_budget) {
        this.spent_budget = spent_budget;
    }

    public String getRemaining_budget() {
        return remaining_budget;
    }

    public void setRemaining_budget(String remaining_budget) {
        this.remaining_budget = remaining_budget;
    }

    @Override
    public String toString() {
        return "manage_annual_budget{" +
                "budgetID='" + budgetID + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", allocated_budget='" + allocated_budget + '\'' +
                ", spent_budget='" + spent_budget + '\'' +
                ", remaining_budget='" + remaining_budget + '\'' +
                '}';
    }
}
