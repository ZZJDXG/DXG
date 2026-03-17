package cn.yonyong.dao;

import cn.yonyong.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao {

  List<User> queryStaffs();

  User queryStaffByID(@Param("staffID") String staffID);

  int countByStaffID(@Param("staffID") String staffID);

  int countByTel(@Param("tel") String tel);

  int countByTelExcludeID(@Param("tel") String tel, @Param("staffID") String staffID);

  int insertStaff(User u);

  int updateStaff(User u);

  int deleteStaffByID(@Param("staffID") String staffID);
}
