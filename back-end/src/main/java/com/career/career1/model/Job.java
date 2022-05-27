package com.career.career1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Job{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int JobId;

    private String email;
    private String jobName;

    private int jobOrder;

    private String salary;
    private String duty;
    private String jobRequirements;
    private String workplace;
    private String updateTime;
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public int getJobId() {
        return JobId;
    }
    public void setJobId(int jobId) {
        JobId = jobId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public int getJobOrder() {
        return jobOrder;
    }
    public void setJobOrder(int jobOrder) {
        this.jobOrder = jobOrder;
    }
    public String getDuty() {
        return duty;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getJobRequirements() {
        return jobRequirements;
    }
    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
    public String getWorkplace() {
        return workplace;
    }
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
}
