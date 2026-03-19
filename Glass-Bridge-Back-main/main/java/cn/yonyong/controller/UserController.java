package cn.yonyong.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.yonyong.request.ChangePasswordRequest;
import cn.yonyong.request.LoginRequest;
import cn.yonyong.request.ResetPasswordRequest;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.yonyong.response.UserDTO;
import cn.yonyong.pojo.User;
import cn.yonyong.service.UserService;
import cn.yonyong.utils.SHA256Utils;

/**
 * @author gugu
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 登录
     * POST /users/login
     * body: { "tel": "...", "password": "..." }
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest body) {
        if (body.getTel() == null || body.getTel().trim().isEmpty()) {
            return Result.error("手机号不能为空");
        }
        if (body.getPassword() == null || body.getPassword().trim().isEmpty()) {
            return Result.error("密码不能为空");
        }
        return userService.login(body);
    }


//一点点不符合风格的代码
    /**
     * 重置密码
     * PUT /users/{tel}/password/reset
     * body: { "tel": "..." }
     */
    @PutMapping("/{tel}/password/reset")
    public Result resetPassword(@PathVariable("tel")String usertel) {
        if (usertel == null || usertel.trim().isEmpty()) {
            return Result.error("手机号不能为空");
        }
        return userService.resetPassword(usertel);
    }

    //一点点不符合风格的代码
    /**
     * 修改密码
     * PUT /users/{tel}/changePassword
     * body: {"oldPassword": "...", "newPassword": "..." }
     */
    @PutMapping("/{tel}/password")
    public Result changePassword(@PathVariable("tel") String usertel,@RequestBody ChangePasswordRequest body) {
        if(usertel == null || usertel.trim().isEmpty()) {
            return Result.error("手机号不能为空");
        }
        if(body.getOldPassword() == null || body.getOldPassword().trim().isEmpty()) {
            return Result.error("旧密码不能为空");
        }
        if(body.getNewPassword() == null || body.getNewPassword().trim().isEmpty()) {
            return Result.error("新密码不能为空");
        }
        return userService.changePassword(usertel, body);
    }

}
