package ddd.demo.application;

import ddd.demo.application.aspect.ApplicationInterceptor;
import ddd.demo.application.aspect.TestBeanPostProcessor;
import ddd.demo.application.order.OrderApplication;
import ddd.demo.application.order.ordercreatedomaineventsubsriber.UpdateEsSubscriber;
import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import easy.domain.event.ISubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = ApplicationInterceptor.class)
@EnableAspectJAutoProxy
public class ApplicationConfig {

    @Autowired
    ApplicationContext context;


    @Bean
    public OrderApplication orderApplication() throws Exception {
        OrderApplication orderApplication = new OrderApplication();

        List<Class<?>> domainEvents = new ArrayList<>();
        domainEvents.add(OrderCreatedDomainEvent.class);


        List<ISubscriber> subscribers = new ArrayList<>();
        subscribers.add(updateEsSubscriber());

        orderApplication.registerDomainEvent(domainEvents);
        orderApplication.registerSubscriber(subscribers);

        return orderApplication;
    }

    @Bean
    public UpdateEsSubscriber updateEsSubscriber() {
        return new UpdateEsSubscriber();
    }

    @Bean
    public TestBeanPostProcessor testBeanPostProcessor() {
        return new TestBeanPostProcessor();
    }
}


