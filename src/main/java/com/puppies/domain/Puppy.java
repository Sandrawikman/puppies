package com.puppies.domain;

public class Puppy {

    private int puppyId;
    private int litterId;
    private String puppyName;
    private String puppyGender;
    private Double puppieWeight;

    public Puppy(int puppyId, int litterId, String puppyName, String puppyGender, Double weight) {
        this.puppyId = puppyId;
        this.litterId = litterId;
        this.puppyName = puppyName;
        this.puppyGender = puppyGender;
        this.puppieWeight = puppieWeight;
    }

    public int getPuppyId() {
        return puppyId;
    }

    public void setPuppyId(int puppyId) {
        this.puppyId = puppyId;
    }

    public int getLitterId() {
        return litterId;
    }

    public void setLitterId(int litterId) {
        this.litterId = litterId;
    }

    public String getPuppyName() {
        return puppyName;
    }

    public void setPuppyName(String puppyName) {
        this.puppyName = puppyName;
    }

    public String getPuppyGender() {
        return puppyGender;
    }

    public void setPuppyGender(String puppyGender) {
        this.puppyGender = puppyGender;
    }

    public Double getpuppieWeight() {
        return puppieWeight;
    }

    public void setPuppieWeight(Double weight) {
        this.puppieWeight = weight;
    }

}
