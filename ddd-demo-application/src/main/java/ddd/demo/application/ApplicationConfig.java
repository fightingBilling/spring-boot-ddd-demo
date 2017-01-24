package ddd.demo.application;

import ddd.demo.application.aspect.ApplicationInterceptor;
import ddd.demo.application.aspect.TestBeanFactory;
import ddd.demo.application.aspect.TestBeanPostProcessor;
import ddd.demo.application.order.OrderApplication;
import ddd.demo.application.order.subscribers.OrderDeliveredUpdateEsSubscriber;
import ddd.demo.application.order.subscribers.OrderCreatedUpdateEsSubscriber;
import ddd.demo.domain.order.event.OrderCreatedDomainEvent;
import ddd.demo.domain.order.event.OrderDeliveredDomainEvent;
import easy.domain.activemqdomainevent.ActiveMqDomainEventManager;
import easy.domain.activemqdomainevent.ActiveMqManager;
import easy.domain.event.ISubscriber;
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

    @Bean
    public ActiveMqDomainEventManager activeMqDomainEventManager() throws Exception {
        return new ActiveMqDomainEventManager(activeMqManager());
    }

    @Bean(destroyMethod = "close")
    public ActiveMqManager activeMqManager() {
        return new ActiveMqManager("tcp://127.0.0.1:61616?wireFormat.maxInactivityDuration=0");
    }


    @Bean
    public OrderApplication orderApplication() throws Exception {
        OrderApplication orderApplication = new OrderApplication(activeMqDomainEventManager());

        List<Class<?>> domainEvents = new ArrayList<>();
        domainEvents.add(OrderCreatedDomainEvent.class);
        domainEvents.add(OrderDeliveredDomainEvent.class);


        List<ISubscriber> subscribers = new ArrayList<>();
        subscribers.add(updateEsSubscriber());
        subscribers.add(orderDeliveredUpdateEsSubscriber());

        orderApplication.registerDomainEvent(domainEvents);
        orderApplication.registerSubscriber(subscribers);

        return orderApplication;
    }

    @Bean
    public OrderCreatedUpdateEsSubscriber updateEsSubscriber() {
        return new OrderCreatedUpdateEsSubscriber();
    }
    @Bean
    public OrderDeliveredUpdateEsSubscriber orderDeliveredUpdateEsSubscriber(){
        return new OrderDeliveredUpdateEsSubscriber();
    }

    @Bean
    public TestBeanFactory testBeanFactory() {
        return new TestBeanFactory();
    }

    @Bean
    public TestBeanPostProcessor testBeanPostProcessor() {
        return new TestBeanPostProcessor();
    }
}


