package cn.yonyong.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yonyong.dao.AttendanceRecordDao;
import cn.yonyong.dao.DepartmentDao;
import cn.yonyong.dao.StaffDao;
import cn.yonyong.pojo.AttendanceRecord;
import cn.yonyong.pojo.Department;
import cn.yonyong.pojo.User;
import cn.yonyong.request.CheckRequest;
import cn.yonyong.service.AttendanceRecordService;
import cn.yonyong.utils.Result;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {

  @Autowired
  private AttendanceRecordDao attendanceRecordDao;

  @Autowired
  private StaffDao staffDao;

  @Autowired
  private DepartmentDao departmentDao;

  @Override
  public Result<?> check(CheckRequest body) {
    if (body == null)
      return Result.error("请求体不能为空");

    String staffID = trim(body.getStaffID());
    Integer type = body.getType(); // 0上班 1下班
    Integer locatedSpot = body.getLocatedSpot();

    if (staffID == null)
      return Result.error("staffID 不能为空");
    if (type == null || (type != 0 && type != 1))
      return Result.error("type 只能是 0(上班) 或 1(下班)");
    if (locatedSpot == null)
      return Result.error("locatedSpot 不能为空");

    // 员工是否存在
    User u = staffDao.queryStaffByID(staffID);
    if (u == null)
      return Result.error("员工不存在");

    // ============ 上班（允许一天多次：上一段必须先下班） ============
    if (type == 0) {
      AttendanceRecord open = attendanceRecordDao.queryLatestTodayOpenRecord(staffID);
      if (open != null)
        return Result.error("请先下班打卡后再上班打卡");

      AttendanceRecord r = new AttendanceRecord();
      r.setStaffID(staffID);
      r.setWorkStartTime(new Date());

      // 默认正常出勤
      int status = 0;

      // 按员工所属部门读取迟到规则
      Integer deptId = u.getStaffDept();
      if (deptId == null) {
        return Result.error("员工未绑定部门");
      }

      Department dept = departmentDao.queryDepartmentDetailById(deptId);
      if (dept == null) {
        return Result.error("员工所属部门不存在");
      }

      if (dept.getLateEnabled() != null && dept.getLateEnabled() == 1) {
        if (isLateNow(dept.getLateStartTime(), dept.getGraceMinutes())) {
          status = 3; // 3=迟到
        }
      }

      r.setAttendanceStatus(status);
      r.setLocatedSpot(locatedSpot);

      int rows = attendanceRecordDao.insertCheckIn(r);
      return rows > 0 ? Result.success("上班打卡成功") : Result.error("上班打卡失败");
    }

    // ============ 下班（更新今天最新一条未下班记录） ============
    AttendanceRecord open = attendanceRecordDao.queryLatestTodayOpenRecord(staffID);
    if (open == null)
      return Result.error("今日没有可下班的打卡记录");

    AttendanceRecord upd = new AttendanceRecord();
    upd.setStaffID(staffID);
    upd.setWorkStartTime(open.getWorkStartTime()); // 用于定位行
    upd.setWorkEndTime(new Date());
    upd.setAttendanceStatus(1); // 1=下班
    upd.setLocatedSpot(locatedSpot);

    int rows = attendanceRecordDao.updateCheckOut(upd);
    return rows > 0 ? Result.success("下班打卡成功") : Result.error("下班打卡失败");
  }

  private static String trim(String s) {
    if (s == null)
      return null;
    String t = s.trim();
    return t.isEmpty() ? null : t;
  }

  /**
   * 判断当前时间是否迟到
   * lateStartTime: 部门表里的时间字符串，如 "09:00:00"
   * graceMinutes: 宽限分钟，如 5
   */
  private static boolean isLateNow(String lateStartTime, Integer graceMinutes) {
    try {
      SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss");
      Date t = timeFmt.parse(lateStartTime);

      Calendar deadline = Calendar.getInstance();
      Calendar now = Calendar.getInstance();

      // 先用今天日期
      deadline.setTime(now.getTime());

      // 再把时分秒替换成规则时间
      Calendar tt = Calendar.getInstance();
      tt.setTime(t);

      deadline.set(Calendar.HOUR_OF_DAY, tt.get(Calendar.HOUR_OF_DAY));
      deadline.set(Calendar.MINUTE, tt.get(Calendar.MINUTE));
      deadline.set(Calendar.SECOND, tt.get(Calendar.SECOND));
      deadline.set(Calendar.MILLISECOND, 0);

      // 加宽限分钟
      if (graceMinutes != null && graceMinutes > 0) {
        deadline.add(Calendar.MINUTE, graceMinutes);
      }

      // 当前时间晚于截止时间 => 迟到
      return now.getTime().after(deadline.getTime());
    } catch (Exception e) {
      return false;
    }
  }
}
