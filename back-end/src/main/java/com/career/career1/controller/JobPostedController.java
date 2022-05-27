package com.career.career1.controller;

import java.util.List;

import com.career.career1.model.JobPosted;
import com.career.career1.repo.JobPostedRepo;

import com.career.career1.model.Job;
import com.career.career1.repo.JobRepo;

import com.career.career1.model.Corporation;
import com.career.career1.repo.CorporationRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JobPostedController {
    @Autowired
    private JobPostedRepo jobPostedRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private CorporationRepo corporationRepo;
    
    public int length() {
        List<JobPosted> updateJobPostedList = jobPostedRepo.findAll();
        int length = 0;
        if (updateJobPostedList.size()!=0) {
            length = updateJobPostedList.get((int) jobPostedRepo.count()-1).getJobId();
        }
        return length;
    }
    public int jobLength() {
        List<Job> updateJobList = jobRepo.findAll();
        int length = 0;
        if (updateJobList.size()!=0) {
            length = updateJobList.get((int) jobRepo.count()-1).getJobId();
        }
        return length;
    }



    // 在人才资源-已发布岗位界面展示已有岗位按钮
    @PostMapping(value = "showPostedJobs")
    // 返回一个字符串：$1:xxx$2:xxx$3:xxx ……
    public String showPostedJobs(@RequestParam(name = "email") String email) {
        String s = "";
        String e = "";
        int length = length();
        int id = 0;
        for (int i = 1; i <= length; i++) {
            if (jobPostedRepo.findById(i).isPresent()) {
                JobPosted j = jobPostedRepo.findById(i).get();
                e = j.getEmail();
                id = j.getJobId();
                if (email.equals(e)){
                    s += "$" + Integer.toString(id) + ":" + j.getJobName();
                }
            }
        }
        return s;
    }


    //在人才资源-已发布岗位详情页面展示已发布岗位信息
    @PostMapping(value = "jobPostedShowing")
    // 根据email,jobOrder返回一个岗位信息的json
    public JobPosted jobPostedShowing(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "postOrder") int postOrder){
        int length = length();
        String e = "";
        int order = 0;
        JobPosted result = new JobPosted();
        for (int i = 1; i <= length; i++) {
            if (jobPostedRepo.findById(i).isPresent()){
                JobPosted j = jobPostedRepo.findById(i).get();
                e = j.getEmail();
                order = j.getPostOrder();
                if (email.equals(e)){
                    if (postOrder == order) {
                        result = j;
                    }
                }
            }
        }
        return result;
    }



    //在求职中心-查看职位页面展示已发布岗位信息
    @PostMapping(value = "careerCenterJobPostedShowing")
    // 根据email,jobOrder返回一个岗位信息的json
    public JobPosted careerCenterJobPostedShowing(@RequestParam(name = "jobId") int id){
        JobPosted result = new JobPosted();
        JobPosted j = jobPostedRepo.findById(id).get();
        if(jobPostedRepo.findById(id).isPresent()){
            result = j;
        }
        return result;
    }



    // 发布新岗位
    @PostMapping(value = "postJob")
    // 返回1代表发布成功；返回0代表岗位名jobName重复
    public int postJob(@RequestParam(name = "email") String email,
                            @RequestParam(name = "jobOrder") int jobOrder,
                            @RequestParam(name = "jobType") String jobType,
                            @RequestParam(name = "industry") String industry,
                            @RequestParam(name = "jobTitle") String jobTitle) {
        int length = length();
        int o = 1;
        String e = "";

        int jobLength = jobLength();
        String em = "";
        int order = 0;
        Job result = new Job();
        for (int i = 1; i <= jobLength; i++) {
            if (jobRepo.findById(i).isPresent()){
                Job j = jobRepo.findById(i).get();
                em = j.getEmail();
                order = j.getJobOrder();
                if (email.equals(em)){
                    if (jobOrder == order) {
                        result = j;
                    }
                }
            }
        }

        String updateTime = result.getUpdateTime();
        String jobName = result.getJobName();
        String salary = result.getSalary();
        String duty = result.getDuty();
        String jobRequirements = result.getJobRequirements();
        String workplace = result.getWorkplace();

        // 检验岗位名jobName是否重复
        int postedJobOrder;
        for (int i = 1; i <= length; i++) {
            if (jobPostedRepo.findById(i).isPresent()){
                JobPosted r = jobPostedRepo.findById(i).get();
                postedJobOrder = r.getJobOrder();
                e = r.getEmail();
                if (email.equals(e)){
                    if (jobOrder==postedJobOrder) {
                        r.setJobName(jobName);
                        r.setSalary(salary);
                        r.setDuty(duty);
                        r.setJobRequirements(jobRequirements);
                        r.setWorkplace(workplace);

                        r.setType(jobType);
                        r.setIndustry(industry);
                        r.setJobTitle(jobTitle);
                        jobPostedRepo.save(r);
                        return 0;
                    }
                }
            }
        }
        // 根据已发布岗位数量设定postOrder
        for (int i = 1; i <= length; i++) {
            if (jobPostedRepo.findById(i).isPresent()){
                JobPosted r = jobPostedRepo.findById(i).get();
                e = r.getEmail();
                if (email.equals(e)){
                    o++;
                }
            }
        }
        JobPosted job = new JobPosted();
        job.setEmail(email);
        job.setPostOrder(o);
        job.setJobName(jobName);
        job.setSalary(salary);
        job.setDuty(duty);
        job.setJobRequirements(jobRequirements);
        job.setWorkplace(workplace);
        job.setUpdateTime(updateTime);
        job.setJobOrder(jobOrder);

        job.setType(jobType);
        job.setIndustry(industry);
        job.setJobTitle(jobTitle);
        jobPostedRepo.save(job);
        
        return 1;
    }



    // 在求职大厅界面根据选项展示发布的岗位
    public class ReturnData {
        private String datails;
        private String salary;
        public void setDatails(String datails){
            this.datails = datails;
        }
        public String getDatails() {
            return datails;
        }
        public void setSalary(String salary){
            this.salary = salary;
        }
        public String getSalary() {
            return salary;
        }
    }
    @PostMapping(value = "showPostedJobs_careerCenter")
    // 返回一个字符串：$1:xxx#sss$2:xxx#sss$3:xxx#sss ……
    public ReturnData showPostedJobs_careerCenter(@RequestParam(name = "jobType") String jobType,
                                              @RequestParam(name = "industry") String industry,
                                              @RequestParam(name = "jobTitle") String jobTitle) {
        String type = "";
        String indu = "";
        String title = "";
        int length = length();
        ReturnData r = new ReturnData();
        for (int i = 1; i <= length; i++) {
            if (jobPostedRepo.findById(i).isPresent()) {
                JobPosted j = jobPostedRepo.findById(i).get();
                type = j.getType();
                indu = j.getIndustry();
                title = j.getJobTitle();
                if (jobType.equals(type)&&industry.equals(indu)&&jobTitle.equals(title)){
                    // 要返回的有：job_id，通过email得到的公司名称，job_name，salary
                    String email = j.getEmail();
                    int corLength = (int) corporationRepo.count();
                    String corName = "";
                    for (int k = 1; k <= corLength; k++) {
                        Corporation cor = corporationRepo.findById(k).get();
                        String e = cor.getEmail();
                        if (email.equals(e)){
                            corName = cor.getCorporateName();
                        }
                    }
                    r.datails += "$" + j.getJobId() + ":" + corName + "#" + j.getJobName();
                    r.salary += "$" + j.getSalary();
                }
            }
        }
        return r;
    }
}
