package cn.yonyong.pojo;

import java.util.Date;

public class User {

    private String staffID; // 表里是 varchar 也好，int 也好，这里用 String 最通用
    private String staffName;
    private Integer staffGender; // 你表里存 0/1
    private Date staffBirthday; // ✅ DATE
    private String staffIDCard;
    private Integer attendanceStatus; // ✅ AttendanceStatus
    private Integer staffDept;
    private String tel;
    private String password;
    private Integer staffPosition;
    private Date hireDate;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(Integer staffGender) {
        this.staffGender = staffGender;
    }

    public Date getStaffBirthday() {
        return staffBirthday;
    }

    public void setStaffBirthday(Date staffBirthday) {
        this.staffBirthday = staffBirthday;
    }

    public String getStaffIDCard() {
        return staffIDCard;
    }

    public void setStaffIDCard(String staffIDCard) {
        this.staffIDCard = staffIDCard;
    }

    public Integer getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Integer attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Integer getStaffDept() {
        return staffDept;
    }

    public void setStaffDept(Integer staffDept) {
        this.staffDept = staffDept;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(Integer staffPosition) {
        this.staffPosition = staffPosition;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
