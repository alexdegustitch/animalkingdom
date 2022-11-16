package com.animals.scheduler.scheduler;

import com.animals.scheduler.jobs.StageJob;
import com.animals.scheduler.jobs.VoteJob;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
@EnableScheduling
public class JobScheduler {
    @Bean()
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean
    public CommandLineRunner run(Scheduler scheduler) {
        return (String[] args) -> {
            /*
            JobDetail voteJob = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("type", "all")
                    .build();
            JobDetail voteJob1 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("type", "notAll")
                    .build();
            JobDetail voteJob2 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez2@gmail.com")
                    .build();
            JobDetail voteJob3 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez3@gmail.com")
                    .build();
            JobDetail voteJob5 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez5@gmail.com")
                    .build();
            JobDetail voteJob6 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez6@gmail.com")
                    .build();
            JobDetail voteJob9 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez9@gmail.com")
                    .build();
            JobDetail voteJob10 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez10@gmail.com")
                    .build();
            JobDetail voteJob11 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez11@gmail.com")
                    .build();
            JobDetail voteJob12 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez12@gmail.com")
                    .build();
            JobDetail voteJob13 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez13@gmail.com")
                    .build();
            JobDetail voteJob15 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez15@gmail.com")
                    .build();
            JobDetail voteJob19 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez19@gmail.com")
                    .build();
            JobDetail voteJob24 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez24@gmail.com")
                    .build();
            JobDetail voteJob25 = JobBuilder.newJob(VoteJob.class)
                    .usingJobData("email", "jez25@gmail.com")
                    .build();
            */
            JobDetail stageJob = JobBuilder.newJob(StageJob.class)
                    .build();
            /*
            Date afterFiveSeconds = Date.from(LocalDateTime.now().plusSeconds(5)
                    .atZone(ZoneId.systemDefault()).toInstant());
            Date afterTenSeconds = Date.from(LocalDateTime.now().plusSeconds(10)
                    .atZone(ZoneId.systemDefault()).toInstant());


            CronTrigger triggerVoteJob1 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();

            CronTrigger triggerVoteJob2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob3 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger3", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob5 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger5", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob6 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger6", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob9 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger9", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob10 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger10", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob11 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger11", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob12 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger12", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob13 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger13", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob15 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger15", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob19 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger19", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob24 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger24", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob25 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger25", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                    .build();
            CronTrigger triggerVoteJob = TriggerBuilder.newTrigger()
                    .withIdentity("triggerVoteJob", "group3")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();*/

            CronTrigger triggerStageJob = TriggerBuilder.newTrigger()
                    .withIdentity("trigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                    .build();


            // scheduler.scheduleJob(voteJob1, triggerVoteJob1);
            //scheduler.scheduleJob(voteJob, triggerVoteJob);
            //scheduler.scheduleJob(voteJob2, triggerVoteJob2);
            //scheduler.scheduleJob(voteJob3, triggerVoteJob3);
            //scheduler.scheduleJob(voteJob5, triggerVoteJob5);
            //scheduler.scheduleJob(voteJob6, triggerVoteJob6);
            //scheduler.scheduleJob(voteJob9, triggerVoteJob9);
            //scheduler.scheduleJob(voteJob10, triggerVoteJob10);
            //scheduler.scheduleJob(voteJob11, triggerVoteJob11);
            //scheduler.scheduleJob(voteJob12, triggerVoteJob12);
            //scheduler.scheduleJob(voteJob13, triggerVoteJob13);
            //scheduler.scheduleJob(voteJob15, triggerVoteJob15);
            //scheduler.scheduleJob(voteJob19, triggerVoteJob19);
            //scheduler.scheduleJob(voteJob24, triggerVoteJob24);
            //scheduler.scheduleJob(voteJob25, triggerVoteJob25);

            scheduler.scheduleJob(stageJob, triggerStageJob);
        };
    }
}
