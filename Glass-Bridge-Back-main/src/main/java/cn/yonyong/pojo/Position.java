package cn.yonyong.pojo;

import java.util.Date;

public class Position {

  private Integer positionID; // 对应 PosID
  private String positionName; // 对应 PosName
  private Date updatedTime; // 对应 UpdatedTime

  public Integer getPositionID() {
    return positionID;
  }

  public void setPositionID(Integer positionID) {
    this.positionID = positionID;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public Date getUpdatedTime() {
    return updatedTime;
  }

  public void setUpdatedTime(Date updatedTime) {
    this.updatedTime = updatedTime;
  }
}
