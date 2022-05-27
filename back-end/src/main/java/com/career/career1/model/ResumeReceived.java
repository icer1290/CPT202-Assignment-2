package com.career.career1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ResumeReceived {
    
    public int getJobReceivedId() {
        return JobReceivedId;
    }
    public void setJobReceivedId(int jobReceivedId) {
        JobReceivedId = jobReceivedId;
    }
    public int getJob_id() {
        return job_id;
    }
    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
    public int getR_order() {
        return r_order;
    }
    public void setR_order(int r_order) {
        this.r_order = r_order;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int JobReceivedId;

    private String email;
    private int job_id;
    
    private String username;
    private int r_order;
}
