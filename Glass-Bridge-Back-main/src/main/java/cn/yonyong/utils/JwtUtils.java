package cn.yonyong.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.yonyong.request.LoginRequest;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
/**
 * @author gugu
 */
public class JwtUtils {

    /**
     * 密钥，可以从配置文件中加载
     */
    private static final byte[] SECRET_KEY = "yourSecretKey".getBytes();

    /**
     * 过期时间（秒）：7 天
     */
    public static final long EXPIRE = 7 * 24 * 60 * 60;

    /**
     * 根据用户信息生成JWT
     * @param data
     * @return
     */
    public static  String generateToken(LoginRequest data) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("tel", data.getTel());
        JWT jwt = JWT.create();
        // 设置携带数据
        payload.forEach(jwt::setPayload);
        // 设置密钥
        jwt.setKey(SECRET_KEY);
        // 设置过期时间
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE * 1000));
        return jwt.sign();
    }

    /**
     * 验证JWT
     * @param token
     * @return
     */
    public static boolean verify (String token) {
        if (StringUtils.isBlank(token)) return false;
        return JWT.of(token).setKey(SECRET_KEY).verify();
    }

    /**
     * token 校验，并获取用户信息
     * @param token token
     * @return userDto
     */
    public static Map<String,Object> verifyAndGetInfo(String token) {
        Map<String,Object> data = new HashMap<>();
        if(!verify(token)) {
            return null;
        }
        // 解析数据
        JWT jwt = JWT.of(token);
//        Long id = Long.valueOf(jwt.getPayload("id").toString());
        String tel = jwt.getPayload("nickname").toString();
        // 返回用户信息
        data.put("tel", tel);
        return data;
    }
}
