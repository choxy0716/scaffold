package com.choxy.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


import javax.annotation.PostConstruct;
import java.util.Map;

public class QuartzService {

    @Autowired
    Scheduler scheduler;

    @PostConstruct
    public void startSchedule() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 新增一个调度任务
     * @param clazz 具体执行类
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     * @param cron cronExpress
     * @param jobData 任务参数
     */
    public void addJob(Class<? extends QuartzJobBean> clazz, String jobName, String jobGroupName, String cron, Map<String, Object> jobData) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobName, jobGroupName).build();
            if (jobData != null && !jobData.isEmpty()) {
                jobDetail.getJobDataMap().putAll(jobData);
            }
            // 定义调度规则
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .startNow()
                    .build();
            // 把作业注册到调度器
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改定时任务的执行周期
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     * @param cron cronExpress
     */
    public void updateJob(String jobName, String jobGroupName, String cron) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey,trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除定时任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     */
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 暂停执行定时任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     */
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 恢复定时任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     */
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 立即运行定时任务
     * @param jobName 任务名称
     * @param jobGroupName 任务分组
     */
    public void runJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            if (!scheduler.isStarted()) {
                scheduler.triggerJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    

}
