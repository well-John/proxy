package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.PUser;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xieyougen
 * @email: xieyougen@tuandai.com
 * @description:
 * @date: 2018/2/1 18:02
 */
public class TestCglibService {
    private DataSource dataSource;

    public TestCglibService(){
    }


    public TestCglibService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.userDao = new UserDao(dataSource);
    }

    private UserDao userDao;


    public void test2() throws Exception{
        List<PUser> list = new ArrayList<>();
        for (int i=1;i<=9;i++){
            PUser user = new PUser();
            user.setId(i);
            user.setSex("男");
            user.setName(String.valueOf(10-i));
            userDao.insert(user);
        }
       /* PUser puser = new PUser();
        puser.setId(9);
        puser.setSex("男");
        puser.setName("11");
        userDao.insert(puser);*/
    }
}
