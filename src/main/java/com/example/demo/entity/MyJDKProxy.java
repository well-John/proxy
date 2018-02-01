package com.example.demo.entity;




import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/2/1 15:58
 */
public class MyJDKProxy {

    private MyTransactionManager myTransactionManager;

    public MyJDKProxy(MyTransactionManager myTransactionManager) {
        this.myTransactionManager = myTransactionManager;
    }

    public Object proxyFor(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new MyProxyHandler(object, myTransactionManager));
    }


}

class MyProxyHandler implements InvocationHandler {

    private Object proxy;

    private MyTransactionManager myTransactionManager;

    public MyProxyHandler(Object proxy, MyTransactionManager myTransactionManager) {
        this.proxy = proxy;
        this.myTransactionManager = myTransactionManager;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        myTransactionManager.start();
        try {
            result = method.invoke(proxy, objects);
            myTransactionManager.commit();
        } catch (Exception e) {
            myTransactionManager.rollback();
        } finally {
            myTransactionManager.close();
        }
        return result;
    }
}

