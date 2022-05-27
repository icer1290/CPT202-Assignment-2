package com.career.career1.controller;

import java.util.List;

import com.career.career1.model.Job;
import com.career.career1.repo.JobRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JobController {
    @Autowired
    private JobRepo jobRepo;
    
    public int length() {
        List<Job> updateJobList = jobRepo.findAll();
        int length = 0;
        if (updateJobList.size()!=0) {
            length = updateJobList.get((int) jobRepo.count()-1).getJobId();
        }
        return length;
    }

    // 在岗位选择界面展示已有岗位按钮
    @PostMapping(value = "showJobs")
    // 返回一个字符串：$1:xxx$2:xxx$3:xxx ……
    public String showJobs(@RequestParam(name = "email") String email) {
        String s = "";
        String e = "";
        int length = length();
        int order = 0;
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()) {
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                order = j.getJobOrder();
                if (email.equals(e)){
                    s += "$" + Integer.toString(order) + ":" + j.getJobName();
                }
            }
        }
        return s;
    }



    //在岗位详情页面展示岗位信息
    @PostMapping(value = "jobShowing")
    // 根据email,jobOrder返回一个岗位信息的json
    public Job jobShowing(@RequestParam(name = "email") String email,
                                @RequestParam(name = "jobOrder") int jobOrder){
        int length = length();
        String e = "";
        int order = 0;
        Job result = new Job();
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                order = j.getJobOrder();
                if (email.equals(e)){
                    if (jobOrder == order) {
                        result = j;
                    }
                }
            }
        }
        return result;
    }



    // 创建新岗位
    @PostMapping(value = "createJob")
    // 返回1代表创建成功；返回0代表岗位名jobName重复
    public int createJob(@RequestParam(name = "email") String email,
                            @RequestParam(name = "updateTime") String updateTime,
                            @RequestParam(name = "jobName") String jobName,
                            @RequestParam(name = "salary") String salary,
                            @RequestParam(name = "duty") String duty,
                            @RequestParam(name = "jobRequirements") String jobRequirements,
                            @RequestParam(name = "workplace") String workplace) {
        int length = length();
        int o = 1;
        String e = "";
        // 检验岗位名jobName是否重复
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job r = jobRepo.findById(i).get();
                e = r.getEmail();
                if (email.equals(e)){
                    if (jobName.equals(r.getJobName())) {
                        return 0;
                    }
                }
            }
        }
        // 根据已有岗位数量设定jobOrder
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job r = jobRepo.findById(i).get();
                e = r.getEmail();
                if (email.equals(e)){
                    o++;
                }
            }
        }
        Job job = new Job();
        job.setEmail(email);
        job.setJobOrder(o);
        job.setJobName(jobName);
        job.setSalary(salary);
        job.setDuty(duty);
        job.setJobRequirements(jobRequirements);
        job.setWorkplace(workplace);
        job.setUpdateTime(updateTime);
        jobRepo.save(job);
        
        return 1;
    }



    //通过email和jobOrder修改(现有岗位)
    @PostMapping(value = "setJob")
    // 返回11代表修改成功；返回00代表岗位名jobName重复
    public int setJob(   @RequestParam(name = "order") int order,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "updateTime") String updateTime,
                            @RequestParam(name = "jobName") String jobName,
                            @RequestParam(name = "salary") String salary,
                            @RequestParam(name = "duty") String duty,
                            @RequestParam(name = "jobRequirements") String jobRequirements,
                            @RequestParam(name = "workplace") String workplace){
        int length = length();
        String e = "";
        // 检验岗位名jobName是否重复
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                if (email.equals(e)&&order!=j.getJobOrder()){
                    if (jobName.equals(j.getJobName())) {
                        return 00;
                    }
                }
            }
        }

        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                if (email.equals(e)){
                    if (order == j.getJobOrder()) {
                        j.setEmail(email);
                        j.setUpdateTime(updateTime);
                        j.setJobName(jobName);
                        j.setSalary(salary);
                        j.setDuty(duty);
                        j.setJobRequirements(jobRequirements);
                        j.setWorkplace(workplace);
                        jobRepo.save(j);
                        break;
                    }
                }
            }
        }
        return 11;
    }



    //根据email,jobOrder删除简历
    @PostMapping(value = "deleteJob")
    // 若不存在该岗位则返回0，存在并删除成功返回1
    public int deleteJob(@RequestParam(name = "email") String email,
                         @RequestParam(name = "jobOrder") Integer jobOrder){
        int result = 0;
        int length = length();
        String e = "";
        int order = 0;
        // 通过遍历找到对应email和jobOrder的岗位并删除
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                order = j.getJobOrder();
                if (email.equals(e)){
                    if (jobOrder == order) {
                        jobRepo.deleteById(i);
                        result = 1;
                        break;
                    }
                }
            }
        }
        if (result == 0) {
            return result;
        }


        
        // 通过对目标用户的所有岗位遍历来重新给jobOrder赋值
        int o = 1;
        for (int i = 1; i <= length; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                e = j.getEmail();
                order = j.getJobOrder();
                if (email.equals(e)){
                    j.setJobOrder(o);
                    jobRepo.save(j);
                    o++;
                }
            }
        }
        return result;
    }
}