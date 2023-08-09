package com.choxy.service;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;

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

    

}
