package ddd.demo.api;

import ddd.demo.application.ApplicationConfig;
import ddd.demo.infrastructure.elasticsearch.ElasticsSearchConfig;
import ddd.demo.infrastructure.repository.RepositoryConfig;
import ddd.demo.infrastructure.taskschedule.TaskScheduleConfig;
import org.slf4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {AopAutoConfiguration.class, DataSourceAutoConfiguration.class})
@Import(value = {RepositoryConfig.class, ElasticsSearchConfig.class, ApplicationConfig.class, TaskScheduleConfig.class})
public class App {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.setBannerMode(Banner.Mode.OFF);


        application.addListeners((e) -> {
            System.out.println(e.getTimestamp());
            Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);
            logger.info(System.getProperty("user.dir"));
        });
        application.run(args);

    }
}
