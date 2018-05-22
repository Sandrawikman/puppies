package com.puppies.domain;

import java.sql.Date;

public class Litter {

    private int litterId;
    private String litterName;
    private Date dateOfBirth;

    public Litter(int litterId, String litterName, Date dateOfBirth) {
        this.litterId = litterId;
        this.litterName = litterName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getLitterId() {
        return litterId;
    }
    public void setLitterId(int litterId) {
        this.litterId = litterId;
    }
    public String getLitterName() {
        return litterName;
    }
    public void setLitterName(String litterName) {
        this.litterName = litterName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
