package com.tom.dao.user;

import com.tom.dao.BaseDao;
import com.tom.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaolmpl implements UserDao{
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode,String userPassword) throws Exception {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (connection != null) {
            String sql = "select * from smbms_user where userCode=? and userPassword=?";
            Object[] params = {userCode,userPassword};
                rs=BaseDao.execute(connection,pstm,rs,sql,params);
                if (rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));

                }
                BaseDao.closeResource(null,pstm,rs);
        }
        return user;
    }
    //修改当前用户密码
    public int updatePwd(Connection connection,  int id, String password) throws Exception {
        PreparedStatement pstm = null;
        int execute=0;
        if (connection != null) {
            String sql = "update smbms.smbms_user set userPassword = ? where id = ? ";
            Object params[] = {password,id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null,pstm,null);
        }


        return execute;
    }
}
