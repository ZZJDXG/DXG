package cn.yonyong.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.yonyong.pojo.AttendanceRecord;

@Repository
public interface AttendanceRecordDao {

  // 查今天最新一条“未下班”的记录（WorkEndTime 为空）
  AttendanceRecord queryLatestTodayOpenRecord(@Param("staffID") String staffID);

  // 上班打卡：插入
  int insertCheckIn(AttendanceRecord r);

  // 下班打卡：更新（用 StaffID + WorkStartTime 定位）
  int updateCheckOut(AttendanceRecord r);
}