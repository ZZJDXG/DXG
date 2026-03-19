package cn.yonyong.pojo;

import lombok.*;

import java.util.Date;

/**
 * @author gugu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // StaffBirthday
    private Date staffBirthday;

    // StaffPosition（角色 0普通员工 1管理员）
    private Integer staffPosition;

    // HireDate
    private Date hireDate;
}
