package com.example.demo.dao;

import com.example.demo.entity.PUser;
import com.example.demo.entity.SingleThreadConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/2/1 16:48
 */
public class UserDao {

    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void insert(PUser user) throws Exception {
        String sql = "insert into p_user(id,name,sex) values(?,?,?)";
        PreparedStatement statement = null;
        Connection connection = null;
        System.out.println(user);
        connection = SingleThreadConnectionHolder.getConnection(dataSource);
        statement = connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getSex());
        statement.executeUpdate();
        statement.close();
    }
}
