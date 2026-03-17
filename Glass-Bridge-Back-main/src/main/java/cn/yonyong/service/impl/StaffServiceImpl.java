package cn.yonyong.service.impl;

import cn.yonyong.dao.StaffDao;
import cn.yonyong.pojo.User;
import cn.yonyong.request.StaffRequest;
import cn.yonyong.service.StaffService;
import cn.yonyong.utils.Result;
import cn.yonyong.utils.SHA256Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StaffServiceImpl implements StaffService {

  @Autowired
  private StaffDao staffDao;

  @Override
  public Result<?> list() {
    return Result.success(staffDao.queryStaffs());
  }

  @Override
  public Result<?> getById(String staffID) {
    String id = trim(staffID);
    if (id == null)
      return Result.error("staffID 不能为空");
    User u = staffDao.queryStaffByID(id);
    return u == null ? Result.error("员工不存在") : Result.success(u);
  }

  @Override
  public Result<?> add(StaffRequest body) {
    if (body == null)
      return Result.error("请求体不能为空");

    String staffID = trim(body.getStaffID());
    String name = trim(body.getStaffName());
    String tel = trim(body.getTel());
    String idCard = trim(body.getStaffIDCard());

    if (staffID == null)
      return Result.error("staffID 不能为空");
    if (name == null)
      return Result.error("staffName 不能为空");
    if (tel == null)
      return Result.error("tel 不能为空");
    if (idCard == null || idCard.length() != 18)
      return Result.error("staffIDCard 必须是18位");

    if (staffDao.countByStaffID(staffID) > 0)
      return Result.error("员工工号已存在");
    if (staffDao.countByTel(tel) > 0)
      return Result.error("手机号已存在");

    // 身份证推算：性别、生日（表里生日是 DATE）
    Integer gender = parseGenderFromIdCard(idCard); // 0女 1男
    Date birthday = parseBirthdayFromIdCard(idCard); // yyyy-MM-dd -> Date
    if (birthday == null)
      return Result.error("身份证生日解析失败");

    String pwdPlain = trim(body.getPassword());
    if (pwdPlain == null)
      pwdPlain = "123456";

    User u = new User();
    u.setStaffID(staffID);
    u.setStaffName(name);
    u.setStaffIDCard(idCard);
    u.setStaffGender(gender);
    u.setStaffBirthday(birthday);

    u.setStaffDept(body.getStaffDept());
    u.setTel(tel);
    u.setPassword(SHA256Utils.sha256(pwdPlain));
    u.setStaffPosition(body.getStaffPosition());

    // 出勤状态：不传默认 0
    u.setAttendanceStatus(body.getAttendanceStatus() == null ? 0 : body.getAttendanceStatus());

    // 入职时间：不传用当前时间
    u.setHireDate(body.getHireDate() == null ? new Date() : body.getHireDate());

    int rows = staffDao.insertStaff(u);
    return rows > 0 ? Result.success("新增成功") : Result.error("新增失败");
  }

  @Override
  public Result<?> update(String staffID, StaffRequest body) {
    String id = trim(staffID);
    if (id == null)
      return Result.error("staffID 不能为空");
    if (body == null)
      return Result.error("请求体不能为空");

    User old = staffDao.queryStaffByID(id);
    if (old == null)
      return Result.error("员工不存在");

    String name = trim(body.getStaffName());
    String tel = trim(body.getTel());
    String idCard = trim(body.getStaffIDCard());

    if (name == null)
      return Result.error("staffName 不能为空");
    if (tel == null)
      return Result.error("tel 不能为空");
    if (idCard == null || idCard.length() != 18)
      return Result.error("staffIDCard 必须是18位");

    if (staffDao.countByTelExcludeID(tel, id) > 0)
      return Result.error("手机号已存在");

    Integer gender = parseGenderFromIdCard(idCard); // 0女 1男
    Date birthday = parseBirthdayFromIdCard(idCard);
    if (birthday == null)
      return Result.error("身份证生日解析失败");

    User u = new User();
    u.setStaffID(id);
    u.setStaffName(name);
    u.setStaffIDCard(idCard);
    u.setStaffGender(gender);
    u.setStaffBirthday(birthday);

    u.setStaffDept(body.getStaffDept());
    u.setTel(tel);
    u.setStaffPosition(body.getStaffPosition());
    u.setAttendanceStatus(body.getAttendanceStatus());
    u.setHireDate(body.getHireDate());

    // 传了 password 才改
    String pwdPlain = trim(body.getPassword());
    if (pwdPlain != null)
      u.setPassword(SHA256Utils.sha256(pwdPlain));

    int rows = staffDao.updateStaff(u);
    return rows > 0 ? Result.success("更新成功") : Result.error("更新失败");
  }

  @Override
  public Result<?> delete(String staffID) {
    String id = trim(staffID);
    if (id == null)
      return Result.error("staffID 不能为空");
    int rows = staffDao.deleteStaffByID(id);
    return rows > 0 ? Result.success("删除成功") : Result.error("员工不存在或已删除");
  }

  private static String trim(String s) {
    if (s == null)
      return null;
    String t = s.trim();
    return t.isEmpty() ? null : t;
  }

  /**
   * 身份证第17位(倒数第二位)：奇数男、偶数女
   * 你的库：0女 1男
   */
  private static Integer parseGenderFromIdCard(String idCard18) {
    char c = idCard18.charAt(16);
    int n = c - '0';
    return (n % 2 == 1) ? 1 : 0; // 奇数男=1，偶数女=0
  }

  /** 从身份证解析生日：YYYYMMDD -> Date */
  private static Date parseBirthdayFromIdCard(String idCard18) {
    try {
      if (idCard18 == null || idCard18.length() != 18)
        return null;
      String yyyy = idCard18.substring(6, 10);
      String mm = idCard18.substring(10, 12);
      String dd = idCard18.substring(12, 14);
      return new SimpleDateFormat("yyyy-MM-dd").parse(yyyy + "-" + mm + "-" + dd);
    } catch (Exception e) {
      return null;
    }
  }
}
