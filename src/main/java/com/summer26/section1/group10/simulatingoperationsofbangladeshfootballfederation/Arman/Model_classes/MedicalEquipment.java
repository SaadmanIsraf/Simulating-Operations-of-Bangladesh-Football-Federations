package com.summer26.section1.group10.simulatingoperationsofbangladeshfootballfederation.Arman.Model_classes;

import java.io.Serializable;

public class MedicalEquipment implements Serializable {

    private int equipmentId;
    private String equipmentName;
    private int quantityAvailable;
    private String condition;
    private String storageLocation;

    public MedicalEquipment() {
    }

    public MedicalEquipment(
            int equipmentId,
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

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStorageLocation() {
        return storageLocation;
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