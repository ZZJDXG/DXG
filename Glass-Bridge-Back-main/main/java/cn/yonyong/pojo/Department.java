package cn.yonyong.pojo;

import java.sql.Timestamp;

/**
 * @author gugu
 */
// 黄1 创建 Department 实体类，这个类是用于后端中信息的存储和传输
public class Department {
    // 对应 数据库departmentID，可以不用一一对应，但是需要知道对应啥
    private Integer departmentID;
    // 对应 数据库departmentName
    private String departmentName;
    // 对应 数据库supportChannels 菜单可视范围，定长12个字符
    private String supportChannels;
    // 对应 数据库updatedTime 自动更新的
    private Timestamp updatedTime;

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSupportChannels() {
        return supportChannels;
    }

    public void setSupportChannels(String supportChannels) {
        this.supportChannels = supportChannels;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }
}
