package com.career.career1.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Resume{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int r_id;

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    private String username;

    private String stu_name;

    private String r_name;

    private int r_order;

    private String r_project;

    private String r_course;

    private String r_award;

    private String r_hobby;

    private String r_skill;

    private String r_evaluate;

    private String r_job;

    private String updateTime;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getR_Order() {
        return r_order;
    }

    public void setR_Order(int r_order) {
        this.r_order = r_order;
    }

    public String getR_project() {
        return r_project;
    }

    public void setR_project(String r_project) {
        this.r_project = r_project;
    }

    public String getR_course() {
        return r_course;
    }

    public void setR_course(String r_course) {
        this.r_course = r_course;
    }

    public String getR_award() {
        return r_award;
    }

    public void setR_award(String r_award) {
        this.r_award = r_award;
    }

    public String getR_hobby() {
        return r_hobby;
    }

    public void setR_hobby(String r_hobby) {
        this.r_hobby = r_hobby;
    }

    public String getR_skill() {
        return r_skill;
    }

    public void setR_skill(String r_skill) {
        this.r_skill = r_skill;
    }

    public String getR_evaluate() {
        return r_evaluate;
    }

    public void setR_evaluate(String r_evaluate) {
        this.r_evaluate = r_evaluate;
    }

    public String getR_job() {
        return r_job;
    }

    public void setR_job(String r_job) {
        this.r_job = r_job;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }



    

}
