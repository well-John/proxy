package com.example.demo.entity;

import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/2/1 18:00
 */
public class MyCglibProxy implements MethodInterceptor{
    private MyTransactionManager myTransactionManager;


    public MyCglibProxy (MyTransactionManager myTransactionManager) {
        this.myTransactionManager = myTransactionManager;
    }

    public Object proxyFor(Object object) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        myTransactionManager.start();
        try {
            result = methodProxy.invokeSuper(o,objects);
            myTransactionManager.commit();
        } catch (Exception e) {
            myTransactionManager.rollback();
        } finally {
            myTransactionManager.close();
        }
        return result;
    }
}
