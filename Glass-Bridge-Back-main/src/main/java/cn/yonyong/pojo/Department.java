package cn.yonyong.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author gugu
 */
@Data
// 黄1 创建 Department 实体类，这个类是用于后端中信息的存储和传输
public class Department {
    // 对应 数据库departmentID，可以不用一一对应，但是需要知道对应啥
    @JsonProperty("DeptID")
    private Integer departmentID;
    // 对应 数据库departmentName
    @JsonProperty("DeptName")
    private String departmentName;
    // 对应 数据库supportChannels 菜单可视范围，定长12个字符
    @JsonProperty("supportChannels")
    private String supportChannels;
    @JsonProperty("SuperiorDept")
    /**-1为一级部门，其余为上级部门ID*/private Integer superiordept;
    @JsonProperty("ShiftType")
    /**0固定班制 1自由工时 2排班制*/private Integer shifttype;
    @JsonProperty("AttendanceDay")
    /**考勤日：对应周一二三四五六七*/private String attendanceday;
    @JsonProperty("AttendanceRule1Start")
    /**考勤规则1上班时间(HHMM)*/private String attendancerule1start;
    @JsonProperty("AttendanceRule1End")
    /**考勤规则1下班时间(HHMM)*/private String attendancerule1end;
    @JsonProperty("AttendanceRule2Start")
    /**考勤规则2上班时间(HHMM)*/private String attendancerule2start;
    @JsonProperty("AttendanceRule2End")
    /**考勤规则2下班时间(HHMM)*/private String attendancerule2end;
    /**强制下班时间(HHMM)*/private String cleartime;


}
