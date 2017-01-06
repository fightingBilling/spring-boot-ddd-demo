package ddd.demo.application;

import ddd.demo.application.aspect.ApplicationInterceptor;
import ddd.demo.application.aspect.TestBeanPostProcessor;
import ddd.demo.application.order.OrderApplication;
import easy.domain.application.ApplicationBuild;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackageClasses = ApplicationInterceptor.class)
@EnableAspectJAutoProxy
public class ApplicationConfig {

    private ApplicationBuild applicationBuild = new ApplicationBuild();

    @Bean
    public OrderApplication orderApplication() throws Exception {
        return applicationBuild.build(new OrderApplication());


    }

    @Bean
    public TestBeanPostProcessor testBeanPostProcessor() {
        return new TestBeanPostProcessor();
    }
}


