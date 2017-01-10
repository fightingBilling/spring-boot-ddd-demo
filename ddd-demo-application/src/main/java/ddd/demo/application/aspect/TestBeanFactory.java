package ddd.demo.application.aspect;

import ddd.demo.application.order.ITestBean;
import org.springframework.beans.factory.FactoryBean;


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