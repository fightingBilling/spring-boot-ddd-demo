package ddd.demo.application.aspect;

import ddd.demo.application.order.IOrderElasticSearchQuery;
import ddd.demo.application.order.ITestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.ResolvableType;


public class TestBeanFactory implements FactoryBean<ITestBean> {

    @Override
    public ITestBean getObject() throws Exception {
        return new ITestBean() {
            @Override
            public void add() {

            }
        };
    }

    @Override
    public Class<?> getObjectType() {
        return ITestBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}