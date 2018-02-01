package com.example.demo.entity;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/1/25 16:00
 */

public class ConnectionHolder {

    private Map<DataSource,Connection> map = new HashMap<>();



    public  Connection getConnectionByDataSource(DataSource dataSource) throws Exception {
        Connection connection = map.get(dataSource);
        if(connection == null || connection.isClosed()){
            connection = dataSource.getConnection();
            map.put(dataSource,connection);
        }
        return connection;
    }

    public void removeConnection(DataSource dataSource)
    {
        map.remove(dataSource);
    }

}
