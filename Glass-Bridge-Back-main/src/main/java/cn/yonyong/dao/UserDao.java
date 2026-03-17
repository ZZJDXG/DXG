package cn.yonyong.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yonyong.pojo.User;

@Repository
public interface UserDao {
    // 查询usr表账号和密码 ---》验证登陆
    // 传递参数为一个对象时，不加@Param注解会报错
    public User queryUser(@Param("user") User user);

    // 查询表中是否已经存在该用户
    public User registerQueryUser(String tel);

    // 查用户
    User queryUserByTel(@Param("tel") String tel);

    // 查当前密码（数据库里 StaffPwd）
    String queryPasswordByTel(@Param("tel") String tel);

    // 注册用户
    public int registerUser(User user);

    // 通过电话号码查询身份证号
    String queryIdCardByTel(@Param("tel") String tel);

    // 通过电话号码修改密码
    int updatePasswordByTel(@Param("tel") String tel, @Param("password") String password);

}
