package ddd.demo.infrastructure.taskschedule;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskScheduleConfig {

    private final Logger logger = LoggerFactory.getLogger(TaskScheduleConfig.class);

    @Bean
    public TaskScheduler taskScheduler() {

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);
        taskScheduler.setErrorHandler(e -> {
            logger.error(e.getMessage(), e);
        });

        taskScheduler.initialize();

        taskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                System.out.println("threadId=" + Thread.currentThread().getId());
                try {
                    Thread.sleep(7000);
                    System.out.println("threadId=" + Thread.currentThread().getId() + "OK");

                } catch (InterruptedException e) {

                }

                System.out.println(String.format("task ThreadPoolTaskScheduler time is %s", DateTime.now().toString()));
            }
        }, 5000);
        return taskScheduler;

    }

}
