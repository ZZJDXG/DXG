package cn.yonyong.request;

public class CheckRequest {

  private String staffID;
  private Integer type; // 0=上班 1=下班
  private Integer locatedSpot; // 打卡点

  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getLocatedSpot() {
    return locatedSpot;
  }

  public void setLocatedSpot(Integer locatedSpot) {
    this.locatedSpot = locatedSpot;
  }
}