package com.career.career1.controller;

import java.util.List;


import com.career.career1.model.ResumeReceived;
import com.career.career1.repo.ResumeReceivedRepo;

import com.career.career1.model.Resume;
import com.career.career1.repo.ResumeRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ResumeReceivedController {
    @Autowired
    private ResumeReceivedRepo resumeReceivedRepo;
    @Autowired
    private ResumeRepo resumeRepo;
    
    public int length() {
        List<ResumeReceived> updateResumeList = resumeReceivedRepo.findAll();
        int length = 0;
        if (updateResumeList.size()!=0) {
            length = updateResumeList.get((int) resumeReceivedRepo.count()-1).getJobReceivedId();
        }
        return length;
    }

    public int resumeLength() {
        List<Resume> updateResumeList = resumeRepo.findAll();
        int length = 0;
        if (updateResumeList.size()!=0) {
            length = updateResumeList.get((int) resumeRepo.count()-1).getR_id();
        }
        return length;
    }



    // 学生对某岗位投递简历
    @PostMapping(value = "sendResume")
    // 返回1代表投递成功；返回0代表已投递过
    public int sendResume(@RequestParam(name = "username") String username,
                            @RequestParam(name = "r_order") int r_order,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "job_id") int job_id) {
        int length = length();
        String u = "";
        int j_id = 0;
        // 检验简历是否重复
        for (int i = 1; i <= length; i++) {
            if (resumeReceivedRepo.findById(i).isPresent()){
                ResumeReceived r = resumeReceivedRepo.findById(i).get();
                j_id = r.getJob_id();
                u = r.getUsername();
                if (job_id==j_id){
                    if (username.equals(u)){
                        return 0;
                    }
                }
            }
        }
        ResumeReceived resume = new ResumeReceived();
        resume.setEmail(email);
        resume.setJob_id(job_id);
        resume.setR_order(r_order);
        resume.setUsername(username);
        resumeReceivedRepo.save(resume);
        
        return 1;
    }



    // 在企业人才资源2界面展示投递者按钮
    public class ReturnObj{
        public String username;
        public String name;
        public String r_order;
        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setR_order(String r_order) {
            this.r_order = r_order;
        }
        public String getR_order() {
            return r_order;
        }
    }
    @PostMapping(value = "showSenders")
    // 返回一个对象
    public ReturnObj showSenders(@RequestParam(name = "email") String email,
                              @RequestParam(name = "jobId") int jobId) {
        String e = "";
        String u = "";
        int o = 0;
        int length = length();
        int resumeLength = resumeLength();
        int id = 0;
        ReturnObj obj = new ReturnObj();
        for (int i = 1; i <= length; i++) {
            if (resumeReceivedRepo.findById(i).isPresent()) {
                ResumeReceived j = resumeReceivedRepo.findById(i).get();
                e = j.getEmail();
                id = j.getJob_id();
                if (email.equals(e)){
                    if (jobId==id){
                        String username = j.getUsername();
                        int r_order = j.getR_order();
                        for (int k = 1; k <= resumeLength; k++) {
                            if (resumeRepo.findById(k).isPresent()){
                                Resume r = resumeRepo.findById(k).get();
                                u = r.getUsername();
                                o = r.getR_Order();
                                if (username.equals(u)&&r_order==o){
                                    obj.username += "$" + username;
                                    obj.name +=  "$" + r.getStu_name();
                                    obj.r_order += "$" +  r_order;
                                }
                            }
                        }
                    }
                }
            }
        }
        return obj;
    }



    // 在企业人才资源3界面展示该学生简历信息
    @PostMapping(value = "showSenderResume")
    // 返回一个对象
    public Resume showSenderResume(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "r_order") int r_order) {
        int resumeLength = resumeLength();
        String u = "";
        int o = 0;
        Resume resumeForReturn = new Resume();
        for (int i = 1; i <= resumeLength; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                o = r.getR_Order();
                if (username.equals(u)&&r_order==o) {
                    resumeForReturn = r;
                }
            }
        }
        return resumeForReturn;
    }
}
