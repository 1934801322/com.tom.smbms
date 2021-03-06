package com.tom.service.user;

import com.tom.dao.BaseDao;
import com.tom.dao.user.UserDao;
import com.tom.dao.user.UserDaolmpl;
import com.tom.pojo.User;


import java.sql.Connection;

public class UserServicelmpl implements UserService{
    //业务层都会调用dao层，所以我们要引入dao层
    private UserDao userDao;
    public UserServicelmpl(){
        userDao = new UserDaolmpl();
    }
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的具体的数据库操作
            user= userDao.getLoginUser(connection, userCode,userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return user;
    }

    public boolean updatePwd(int id, String pwd) {
        Connection connection =null;
        boolean flag=false;
        //修改密码
        try {
            connection =BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,pwd)>0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection,null,null);
        }
        return  flag;
    }


}


