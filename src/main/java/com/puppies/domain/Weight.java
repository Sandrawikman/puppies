package com.puppies.domain;

import java.sql.Date;

public class Weight {

    private int weightId;
    private int puppyId;
    private double weight;
    private Date createdAt;

    public Weight(int weightId, int puppyId, double weight, Date createdAt) {
        this.weightId = weightId;
        this.puppyId = puppyId;
        this.weight = weight;
        this.createdAt = createdAt;
    }

    public int getWeightId() {
        return weightId;
    }

    public int getPuppyId() {
        return puppyId;
    }

    public double getWeight() {
        return weight;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
