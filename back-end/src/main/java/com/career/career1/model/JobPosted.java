package com.career.career1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class JobPosted{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int JobId;

    private String email;
    private String jobName;

    public int getPostOrder() {
        return postOrder;
    }
    public void setPostOrder(int postOrder) {
        this.postOrder = postOrder;
    }
    private int postOrder;
    private int jobOrder;

    public int getJobOrder() {
        return jobOrder;
    }
    public void setJobOrder(int jobOrder) {
        this.jobOrder = jobOrder;
    }

    private String salary;
    private String duty;
    private String jobRequirements;
    private String workplace;
    private String updateTime;
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    private String industry;
    private String jobTitle;
    public String getUpdateTime() {
        return updateTime;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
