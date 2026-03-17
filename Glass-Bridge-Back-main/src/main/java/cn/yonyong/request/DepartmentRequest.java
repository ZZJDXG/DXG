package cn.yonyong.request;

/**
 * @author gugu
 */
public class DepartmentRequest {
    private String departmentName;

    // 16位字符串（按你现有业务逻辑）
    private String supportChannels;

    // 迟到规则（已并入部门表）
    private String lateStartTime;
    private Integer graceMinutes;
    private Integer lateEnabled;

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

    public String getLateStartTime() {
        return lateStartTime;
    }

    public void setLateStartTime(String lateStartTime) {
        this.lateStartTime = lateStartTime;
    }

    public Integer getGraceMinutes() {
        return graceMinutes;
    }

    public void setGraceMinutes(Integer graceMinutes) {
        this.graceMinutes = graceMinutes;
    }

    public Integer getLateEnabled() {
        return lateEnabled;
    }

    public void setLateEnabled(Integer lateEnabled) {
        this.lateEnabled = lateEnabled;
    }
}
