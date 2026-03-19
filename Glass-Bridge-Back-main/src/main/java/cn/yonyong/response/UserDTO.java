package cn.yonyong.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 仅用于对前端返回的用户信息（不返回 password / 身份证等敏感字段）
 * @author gugu
 */
@Data
public class UserDTO {

  private String staffID;
  private String staffName;
  private Integer staffGender;
  private Integer staffDept;
  private Date staffBirthday;

  @JsonProperty("tel")
  private String tel;

  @JsonProperty("staffPosition")
  private Integer staffPosition;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
  private Date hireDate;

}
