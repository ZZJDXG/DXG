package cn.yonyong.request;

/**
 * @author gugu
 */
public class DepartmentRequest {
    private String departmentName;

    // 16位字符串（按你现有业务逻辑）
    private String supportChannels;

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
}
