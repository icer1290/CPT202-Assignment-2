package com.career.career1.controller;

import java.util.List;


import com.career.career1.model.Resume;
import com.career.career1.repo.ResumeRepo;

import com.career.career1.model.ResumeReceived;
import com.career.career1.repo.ResumeReceivedRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ResumeController {
    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private ResumeReceivedRepo resumeReceivedRepo;
    
    public int length() {
        List<Resume> updateResumeList = resumeRepo.findAll();
        int length = 0;
        if (updateResumeList.size()!=0) {
            length = updateResumeList.get((int) resumeRepo.count()-1).getR_id();
        }
        return length;
    }

    public int resumeReceivedLength() {
        List<ResumeReceived> updateResumeList = resumeReceivedRepo.findAll();
        int length = 0;
        if (updateResumeList.size()!=0) {
            length = updateResumeList.get((int) resumeReceivedRepo.count()-1).getJobReceivedId();
        }
        return length;
    }
    


    // 在简历选择界面展示已有简历按钮
    @PostMapping(value = "showResumes")
    // 返回一个字符串：$1:xxx$2:xxx$3:xxx ……
    public String showResumes(@RequestParam(name = "username") String username) {
        String s = "";
        String u = "";
        int length = length();
        int order = 0;
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()) {
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                order = r.getR_Order();
                if (username.equals(u)){
                    s += "$" + Integer.toString(order) + ":" + r.getR_name();
                }
            }
        }
        return s;
    }



    //在简历详情页面展示简历信息
    @PostMapping(value = "resumeShowing")
    // 根据username,r_order返回一个简历信息的json
    public Resume resumeShowing(@RequestParam(name = "username") String username,
                                @RequestParam(name = "r_order") int r_order){
        int length = length();
        String u = "";
        int order = 0;
        Resume result = new Resume();
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                order = r.getR_Order();
                if (username.equals(u)){
                    if (r_order == order) {
                        result = r;
                    }
                }
            }
        }
        return result;
    }



    // 创建新简历
    @PostMapping(value = "createResume")
    // 返回1代表创建成功；返回0代表简历名r_name重复
    public int createResume(@RequestParam(name = "username") String username,
                            @RequestParam(name = "updateTime") String updateTime,
                            @RequestParam(name = "r_name") String r_name,
                            @RequestParam(name = "stu_name") String stu_name,
                            @RequestParam(name = "r_project") String r_project,
                            @RequestParam(name = "r_course") String r_course,
                            @RequestParam(name = "r_award") String r_award,
                            @RequestParam(name = "r_hobby") String r_hobby,
                            @RequestParam(name = "r_skill") String r_skill,
                            @RequestParam(name = "r_evaluate") String r_evaluate,
                            @RequestParam(name = "r_job") String r_job) {
        int length = length();
        int o = 1;
        String u = "";
        // 检验简历名r_name是否重复
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                if (username.equals(u)){
                    if (r_name.equals(r.getR_name())) {
                        return 0;
                    }
                }
            }
        }
        // 根据已有简历数量设定r_order
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                if (username.equals(u)){
                    o++;
                }
            }
        }
        Resume resume = new Resume();
        resume.setUsername(username);
        resume.setR_Order(o);
        resume.setUpdateTime(updateTime);
        resume.setR_name(r_name);
        resume.setStu_name(stu_name);
        resume.setR_project(r_project);
        resume.setR_course(r_course);
        resume.setR_award(r_award);
        resume.setR_hobby(r_hobby);
        resume.setR_skill(r_skill);
        resume.setR_evaluate(r_evaluate);
        resume.setR_job(r_job);
        resumeRepo.save(resume);
        
        return 1;
    }



    //通过username和resumeOrder保存和修改(现有简历)
    @PostMapping(value = "setResume")
    // 返回11代表修改成功；返回00代表简历名r_name重复
    public int setResume(   @RequestParam(name = "order") int order,
                            @RequestParam(name = "username") String username,
                            @RequestParam(name = "updateTime") String updateTime,
                            @RequestParam(name = "r_name") String r_name,
                            @RequestParam(name = "stu_name") String stu_name,
                            @RequestParam(name = "r_project") String r_project,
                            @RequestParam(name = "r_course") String r_course,
                            @RequestParam(name = "r_award") String r_award,
                            @RequestParam(name = "r_hobby") String r_hobby,
                            @RequestParam(name = "r_skill") String r_skill,
                            @RequestParam(name = "r_evaluate") String r_evaluate,
                            @RequestParam(name = "r_job") String r_job){
        int length = length();
        String u = "";
        // 检验简历名r_name是否重复
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                if (username.equals(u)&&order!=r.getR_Order()){
                    if (r_name.equals(r.getR_name())) {
                        return 00;
                    }
                }
            }
        }

        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                if (username.equals(u)){
                    if (order == r.getR_Order()) {
                        r.setUsername(username);
                        r.setUpdateTime(updateTime);
                        r.setR_name(r_name);
                        r.setStu_name(stu_name);
                        r.setR_project(r_project);
                        r.setR_course(r_course);
                        r.setR_award(r_award);
                        r.setR_hobby(r_hobby);
                        r.setR_skill(r_skill);
                        r.setR_evaluate(r_evaluate);
                        r.setR_job(r_job);
                        resumeRepo.save(r);
                        break;
                    }
                }
            }
        }
        return 11;
    }



    //根据username,resumeOrder删除简历
    @PostMapping(value = "deleteResume")
    // 若不存在该简历则返回0，存在并删除成功返回1
    public int deleteResumeById(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "r_order") Integer r_order){
        int result = 0;
        int length = length();
        int resumeReceivedLength = resumeReceivedLength();
        String u = "";
        int order = 0;
        // 通过遍历找到对应username和resumeOrder的简历并删除
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                order = r.getR_Order();
                if (username.equals(u)){
                    if (r_order == order) {
                        resumeRepo.deleteById(i);

                        for (int j = 1; j <= resumeReceivedLength; j++) {
                            if (resumeReceivedRepo.findById(j).isPresent()){
                                ResumeReceived resumeReceived = resumeReceivedRepo.findById(j).get();
                                String uu = resumeReceived.getUsername();
                                int oo = resumeReceived.getR_order();
                                if (username.equals(uu)&&r_order==oo) {
                                    resumeReceivedRepo.deleteById(j);
                                }
                            }
                        }

                        result = 1;
                        break;
                    }
                }
            }
        }
        if (result == 0) {
            return result;
        }

        
        // 通过对目标用户的所有简历遍历来重新给resumeOrder赋值
        int o = 1;
        int orderNow = 0;
        for (int i = 1; i <= length; i++) {
            if (resumeRepo.findById(i).isPresent()){
                Resume r = resumeRepo.findById(i).get();
                u = r.getUsername();
                order = r.getR_Order();
                if (username.equals(u)){

                    orderNow = r.getR_Order();
                    for (int j = 1; j <= resumeReceivedLength; j++) {
                        if (resumeReceivedRepo.findById(j).isPresent()){
                            ResumeReceived resumeReceived = resumeReceivedRepo.findById(j).get();
                            String uu = resumeReceived.getUsername();
                            int oo = resumeReceived.getR_order();
                            if (username.equals(uu)&&orderNow==oo) {
                                resumeReceived.setR_order(o);
                                resumeReceivedRepo.save(resumeReceived);
                            }
                        }
                    }

                    r.setR_Order(o);
                    resumeRepo.save(r);
                    o++;
                }
            }
        }
        return result;
    }
}
