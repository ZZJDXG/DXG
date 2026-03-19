package cn.yonyong.request;

import lombok.*;

/**
 * @author gugu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private String departmentName;

    // 16位字符串（按你现有业务逻辑）
    private String supportChannels;

}
