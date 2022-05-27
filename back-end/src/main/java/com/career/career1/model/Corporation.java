package com.career.career1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Corporation { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CorId;

    private String CorporateName;
    private String password;
    private String email;
    private String address;
    private String TypeOfCoprorate;
    private String duty;

    public String getCorporateName() {
        return CorporateName;
    }

    public void setCorporateName(String CorporateName) {
        this.CorporateName = CorporateName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeOfCoprorate() {
        return TypeOfCoprorate;
    }

    public void setTypeOfCoprorate(String typeOfCoprorate) {
        TypeOfCoprorate = typeOfCoprorate;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

}
