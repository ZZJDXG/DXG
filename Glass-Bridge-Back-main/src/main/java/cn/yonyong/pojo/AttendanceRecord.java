package cn.yonyong.pojo;

import java.util.Date;

public class AttendanceRecord {

  private String staffID; // StaffID
  private Date workStartTime; // WorkStartTime
  private Date workEndTime; // WorkEndTime
  private Integer attendanceStatus; // AttendanceStatus
  private Integer locatedSpot; // LocatedSpot

  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  public Date getWorkStartTime() {
    return workStartTime;
  }

  public void setWorkStartTime(Date workStartTime) {
    this.workStartTime = workStartTime;
  }

  public Date getWorkEndTime() {
    return workEndTime;
  }

  public void setWorkEndTime(Date workEndTime) {
    this.workEndTime = workEndTime;
  }

  public Integer getAttendanceStatus() {
    return attendanceStatus;
  }

  public void setAttendanceStatus(Integer attendanceStatus) {
    this.attendanceStatus = attendanceStatus;
  }

  public Integer getLocatedSpot() {
    return locatedSpot;
  }

  public void setLocatedSpot(Integer locatedSpot) {
    this.locatedSpot = locatedSpot;
  }
}