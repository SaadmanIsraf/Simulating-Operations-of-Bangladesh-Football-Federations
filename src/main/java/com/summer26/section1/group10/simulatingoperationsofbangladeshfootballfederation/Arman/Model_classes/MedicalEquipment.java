package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;

public class MedicalEquipment implements Serializable {

    private int equipmentId;
    private String equipmentName;
    private int quantityAvailable;
    private String condition;
    private String storageLocation;

    // Default Constructor
    public MedicalEquipment() {
    }

    // Parameterized Constructor
    public MedicalEquipment(int equipmentId,
                            String equipmentName,
                            int quantityAvailable,
                            String condition,
                            String storageLocation) {

        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantityAvailable = quantityAvailable;
        this.condition = condition;
        this.storageLocation = storageLocation;
    }

    // Getters

    public int getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public String getCondition() {
        return condition;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    // Setters

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    @Override
    public String toString() {
        return "MedicalEquipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                ", condition='" + condition + '\'' +
                ", storageLocation='" + storageLocation + '\'' +
                '}';
    }
}