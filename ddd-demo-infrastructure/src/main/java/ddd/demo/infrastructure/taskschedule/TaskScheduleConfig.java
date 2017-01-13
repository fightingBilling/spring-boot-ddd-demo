package ddd.demo.infrastructure.taskschedule;


import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskScheduleConfig {

    @Bean
    public TaskScheduler taskScheduler() {

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);

        taskScheduler.initialize();
        taskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("task ThreadPoolTaskScheduler time is %s", DateTime.now().toString()));
            }
        }, 5000);
        return taskScheduler;

    }

}
