package cn.yonyong.service;

import cn.yonyong.pojo.User;
import cn.yonyong.request.ChangePasswordRequest;
import cn.yonyong.request.LoginRequest;
import cn.yonyong.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface UserService {

    public Result login(LoginRequest body);

    public Result resetPassword(String usertel);

    public Result changePassword(String usertel,ChangePasswordRequest body);

}
