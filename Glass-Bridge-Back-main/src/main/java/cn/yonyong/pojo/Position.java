package cn.yonyong.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author gugu
 */
@Data
public class Position {

  private Integer positionID; // 对应 PosID
  private String positionName; // 对应 PosName
  private Date updatedTime; // 对应 UpdatedTime

}
