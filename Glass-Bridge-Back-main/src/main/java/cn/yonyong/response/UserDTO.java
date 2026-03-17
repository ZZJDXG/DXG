package cn.yonyong.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 仅用于对前端返回的用户信息（不返回 password / 身份证等敏感字段）
 * @author gugu
 */
public class UserDTO {

  private String staffID;
  private String staffName;
  private Integer staffGender;
  private Integer staffDept;

  @JsonProperty("tel")
  private String tel;

  @JsonProperty("staffPosition")
  private Integer staffPosition;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
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
