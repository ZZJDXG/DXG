package cn.yonyong.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gugu
 */
@Data
public class ChangePasswordRequest {
    private String tel;
    private String oldPassword;
    private String newPassword;

}
