package com.example.demo.entity;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/1/25 16:18
 */

public class SingleThreadConnectionHolder {

    private static final ThreadLocal<ConnectionHolder> threadlocal = new ThreadLocal<ConnectionHolder>();


    public static ConnectionHolder getConnectionHolder(){
        ConnectionHolder connectionHolder = threadlocal.get();
        if(connectionHolder == null){
            connectionHolder = new ConnectionHolder();
            threadlocal.set(connectionHolder);
        }
        return connectionHolder;
    }

    public static Connection getConnection(DataSource dataSource) throws Exception {
        return getConnectionHolder().getConnectionByDataSource(dataSource);
    }

    public static void removeConnection(DataSource dataSource) {
        getConnectionHolder().removeConnection(dataSource);
    }
}
