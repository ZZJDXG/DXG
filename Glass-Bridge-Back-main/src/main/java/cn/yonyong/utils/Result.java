package cn.yonyong.utils;



import java.io.Serializable;

/**
 * 统一返回对象R
 *
 * @author gugu
 */



public class Result<T> implements Serializable {

    private static final long serialVersionUID = 537035925594301155L;
    //状态码, 200成功, 500失败
    private Integer code;
    private T data;
    private String message;

    public Result(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // 成功的静态方法
    public static <T> Result<T> success(T data) {
        return new Result<>(200, data, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(200, null, null);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(200, null, message);
    }

    // 失败的静态方法
    public static <T> Result<T> error(String message) {
        return new Result<>(500, null, message);
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
