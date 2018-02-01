package com.example.demo.annotation;

import com.example.demo.entity.MyTransactionManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/1/24 9:24
 */
@Aspect
@Component
public class MyAnnotationAspect {

    private Logger logger = LoggerFactory.getLogger(MyAnnotationAspect.class);

    @Autowired
    public MyTransactionManager myTransactionManager;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public SqlSessionFactory sqlSessionFactory;



    @Pointcut("@annotation(com.example.demo.annotation.MyAnnotation)")
    public void pointcut(){}

    @Around("@annotation(myname)")
    public Object process(ProceedingJoinPoint joinPoint, MyAnnotation myname) throws Throwable {
        try(Connection connection = dataSource.getConnection();
            SqlSession session = sqlSessionFactory.openSession(connection);) {


        }catch (Exception e){
            logger.info("异常信息：{}",e.getMessage());
        }

        return joinPoint.proceed();
    }

  /*  @Before("@annotation(myname)")
    public void before(JoinPoint joinPoint, MyAnnotation myname)throws Throwable {
        logger.info("当前数据源为：{}"+dataSource.toString());
        Connection connection = connectionHolder.getConnectionByDataSource(dataSource);
        transactionManager.getConnection();
        transactionManager.start();
    }

    @After("@annotation(myname)")
    public void after(JoinPoint joinPoint, MyAnnotation myname){
        logger.info("当前数据源为：{}"+dataSource.toString());
        try {
            Connection connection = connectionHolder.getConnectionByDataSource(dataSource);
            transactionManager.getConnection();
        }catch (Exception e){

        }

    }*/
}
