package cn.yonyong.service.impl;

import cn.yonyong.request.ChangePasswordRequest;
import cn.yonyong.request.LoginRequest;
import cn.yonyong.response.UserDTO;
import cn.yonyong.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yonyong.dao.UserDao;
import cn.yonyong.pojo.User;
import cn.yonyong.service.UserService;
import cn.yonyong.utils.SHA256Utils;

import java.util.HashMap;
import java.util.Map;

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
        dto.setStaffPosition(u.getStaffPosition());
        dto.setHireDate(u.getHireDate());
        return dto;
    }

    @Override
    public Result login(LoginRequest body) {
        try {
            String usertel = body.getTel();
            String password = body.getPassword();

            User existsUser = userDao.registerQueryUser(usertel.trim());
            if (existsUser == null) {
                return Result.error("User does not exist");
            }

            String encrypted = SHA256Utils.sha256(password);
            User loginUser = new User();
            loginUser.setTel(usertel.trim());
            loginUser.setPassword(encrypted);
            User rsUser = userDao.queryUser(loginUser);
            if (rsUser == null) {
                return Result.error("Incorrect password");
            }

            Map<String, Object> data = new HashMap<>();
            data.put("token", "codespaces-dev-token-" + rsUser.getStaffID());
            data.put("user", toDTO(rsUser));
            return Result.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Login failed");
        }
    }

    @Override
    public Result resetPassword(String usertel) {
        try {
            User user = userDao.queryUserByTel(usertel);
            if (user == null) {
                return Result.error("Phone number or ID card information is invalid");
            }

            String idCard = user.getStaffIDCard();
            if (idCard == null || idCard.length() < 6) {
                return Result.error("Phone number or ID card information is invalid");
            }

            String last6 = idCard.substring(idCard.length() - 6);
            String newPwd = SHA256Utils.sha256(last6);
            boolean success = userDao.updatePasswordByTel(usertel, newPwd) > 0;

            if (!success) {
                return Result.error("Reset password failed");
            }

            User fresh = userDao.queryUserByTel(usertel.trim());
            return Result.success(fresh == null ? null : toDTO(fresh));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Reset password failed");
        }
    }

    @Override
    public Result changePassword(String usertel, ChangePasswordRequest body) {
        try {
            String oldPassword = body.getOldPassword();
            String newPassword = body.getNewPassword();

            User exists = userDao.registerQueryUser(usertel.trim());
            if (exists == null) {
                return Result.error("User does not exist");
            }

            String oldSha = SHA256Utils.sha256(oldPassword);
            String newSha = SHA256Utils.sha256(newPassword);
            String currentPwd = userDao.queryPasswordByTel(usertel.trim());
            if (currentPwd == null) {
                return Result.error("User does not exist");
            }
            if (!currentPwd.equals(oldSha)) {
                return Result.error("Current password is incorrect");
            }

            boolean ok = userDao.updatePasswordByTel(usertel.trim(), newSha) > 0;
            if (ok) {
                return Result.success("Password updated successfully");
            }
            return Result.error("Password update failed");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Password update failed");
        }
    }
}
