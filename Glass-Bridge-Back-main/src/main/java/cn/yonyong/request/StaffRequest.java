package cn.yonyong.request;

import java.util.Date;

public class StaffRequest {
    private String staffID;
    private String staffName;
    private Integer staffGender;        // 0/1（也可以不传，由身份证推算）
    private String staffBirthday;       // CHAR(4) 例如 "MMDD"
    private Integer staffDept;          // DeptID
    private String tel;                 // 11位
    private String password;            // 明文（Service 内做 SHA256）
    private Integer staffPosition;      // PosID
    private String staffIDCard;         // 18位
    private Integer attendanceStatus;   // 默认0
    private Date hireDate;

    // getters/setters 省略可用IDE生成
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public Integer getStaffGender() { return staffGender; }
    public void setStaffGender(Integer staffGender) { this.staffGender = staffGender; }
    public String getStaffBirthday() { return staffBirthday; }
    public void setStaffBirthday(String staffBirthday) { this.staffBirthday = staffBirthday; }
    public Integer getStaffDept() { return staffDept; }
    public void setStaffDept(Integer staffDept) { this.staffDept = staffDept; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Integer getStaffPosition() { return staffPosition; }
    public void setStaffPosition(Integer staffPosition) { this.staffPosition = staffPosition; }
    public String getStaffIDCard() { return staffIDCard; }
    public void setStaffIDCard(String staffIDCard) { this.staffIDCard = staffIDCard; }
    public Integer getAttendanceStatus() { return attendanceStatus; }
    public void setAttendanceStatus(Integer attendanceStatus) { this.attendanceStatus = attendanceStatus; }
    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
}
