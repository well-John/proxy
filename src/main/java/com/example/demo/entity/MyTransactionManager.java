package com.example.demo.entity;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/1/25 16:29
 */
@Configuration
public class MyTransactionManager {

    private Logger logger = LoggerFactory.getLogger(MyTransactionManager.class);

    @Autowired
    private DataSource dataSource;

    public Connection getConnection() throws Exception {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

    public void start() throws Exception{
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public void commit()throws Exception{
            Connection connection = getConnection();
            connection.commit();
    }

    public void rollback() throws Exception{
            Connection connection = getConnection();
            connection.rollback();

    }

    public void close(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
        } catch (Exception e) {
            logger.error("连接关闭失败");
            e.printStackTrace();
        }
        SingleThreadConnectionHolder.removeConnection(dataSource);
    }
}
