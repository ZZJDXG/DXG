package cn.yonyong.pojo;

import java.util.Date;

public class User {

    // StaffID
    private String staffID;

    // StaffName
    private String staffName;

    // StaffGender
    private Integer staffGender;

    // StaffIDCard
    private String staffIDCard;

    // StaffDept
    private Integer staffDept;

    // StaffTel
    private String tel;

    // StaffPwd
    private String password;

    // StaffPosition（角色 0普通员工 1管理员）
    private Integer staffPosition;

    // HireDate
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

    public String getStaffIDCard() {
        return staffIDCard;
    }

    public void setStaffIDCard(String staffIDCard) {
        this.staffIDCard = staffIDCard;
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
