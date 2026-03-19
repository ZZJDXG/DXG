package cn.yonyong.service.impl;

import cn.yonyong.request.ChangePasswordRequest;
import cn.yonyong.request.LoginRequest;
import cn.yonyong.response.UserDTO;
import cn.yonyong.utils.JwtUtils;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yonyong.dao.UserDao;
import cn.yonyong.pojo.User;
import cn.yonyong.service.UserService;
import cn.yonyong.utils.SHA256Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gugu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setStaffID(u.getStaffID());
        dto.setStaffName(u.getStaffName());
        dto.setStaffGender(u.getStaffGender());
        dto.setStaffDept(u.getStaffDept());
        dto.setTel(u.getTel());
        dto.setStaffBirthday(u.getStaffBirthday());
        dto.setStaffPosition(u.getStaffPosition());
        dto.setHireDate(u.getHireDate());
        return dto;
    }
    @Override
    public Result login(LoginRequest body) {

        try {
            String usertel = body.getTel();
            String password = body.getPassword();

            // 0) 先查手机号是否存在
            User existsUser = userDao.registerQueryUser(usertel.trim());
            if (existsUser == null) {
                return Result.error("该用户不存在");
            }
            // 1) 校验密码（SHA256）
            String encrypted = SHA256Utils.sha256(password);
            User loginUser = new User();
            loginUser.setTel(usertel.trim());
            loginUser.setPassword(encrypted);
            User rs_user = userDao.queryUser(loginUser);
            if (rs_user == null) {
                return Result.error("密码错误");
            }
            //token
            Map<String, Object> data = new HashMap<>();
            String token = JwtUtils.generateToken(body);
            data.put("token", token);
            data.put("user", toDTO(rs_user));
            return Result.success(data);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("登录异常");
        }
    }

    @Override
    public Result resetPassword(String usertel) {
        try {
            // 1️⃣ 根据手机号查用户（含身份证）
            User user = userDao.queryUserByTel(usertel);
            if (user == null) {
                return Result.error("手机号不存在或身份证信息异常");
            }

            // 2️⃣ 取身份证
            String idCard = user.getStaffIDCard();
            if (idCard == null || idCard.length() < 6) {
                return Result.error("手机号不存在或身份证信息异常");
            }

            // 3️⃣ 身份证后6位
            String last6 = idCard.substring(idCard.length() - 6);

            // 4️⃣ SHA-256 加密
            String newPwd = SHA256Utils.sha256(last6);

            boolean success = userDao.updatePasswordByTel(usertel, newPwd) > 0;

            if (!success) {
                return Result.error("重置失败");
            }

            // 重置成功：查询该员工完整信息并返回 DTO
            User fresh = userDao.queryUserByTel(usertel.trim());
            return Result.success(fresh == null ? null : toDTO(fresh));

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("重置异常");
        }
    }

    @Override
    public Result changePassword(String usertel, ChangePasswordRequest body) {

        try {

            String oldPassword = body.getOldPassword();
            String newPassword = body.getNewPassword();


            // 先判断用户是否存在（为了区分“用户不存在”和“旧密码错误”）
            User exists = userDao.registerQueryUser(usertel.trim());
            if (exists == null) {
                return Result.error("该用户不存在");
            }

            // 前端传明文，这里后端统一 SHA256
            String oldSha = SHA256Utils.sha256(oldPassword);
            String newSha = SHA256Utils.sha256(newPassword);

            String currentPwd = userDao.queryPasswordByTel(usertel.trim());
            if (currentPwd == null) {
                return Result.error("该用户不存在");
            }
            if (!currentPwd.equals(oldSha)) {
                return Result.error("旧密码错误");
            }

            boolean ok = userDao.updatePasswordByTel(usertel.trim(), newSha) > 0;

            if (ok) {
                return Result.success("修改成功");
            } else {
                return Result.error("旧密码错误或修改失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改异常");
        }
    }


}
