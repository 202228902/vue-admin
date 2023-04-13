package com.galaxy.empvue.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code; // 响应状态码
    private String message; // 响应信息
    private T data; // 响应数据

    // 无参构造函数，默认响应状态码为 200，响应信息为 "success"
    public R() {
        this.code = HttpStatus.OK.value();
        this.message = "success";
    }

    // 带有响应数据的构造函数，响应状态码为 200，响应信息为 "success"
    public R(T data) {
        this();
        if (data != null) {
            this.data = data;
        }
    }

    // 自定义响应状态码和响应信息的构造函数
    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 带有响应数据、响应状态码和响应信息的构造函数
    public R(T data, int code, String message) {
        this(code, message);
        if (data != null) {
            this.data = data;
        }
    }

    // 返回一个默认的成功响应对象，响应状态码为 200，响应信息为 "success"
    public static <T> R<T> success() {
        return new R<>();
    }

    // 返回一个带有响应数据的成功响应对象，响应状态码为 200，响应信息为 "success"
    public static <T> R<T> success(T data) {
        return new R<>(data);
    }

    // 返回一个带有自定义响应信息的成功响应对象，响应状态码为 200
    public static <T> R<T> success(String message) {
        return new R<T>().setMessage(message);
    }

    // 返回一个带有响应数据和自定义响应信息的成功响应对象，响应状态码为 200
    public static <T> R<T> success(T data, String message) {
        return new R<>(data).setMessage(message);
    }

    // 返回一个带有自定义响应状态码和响应信息的失败响应对象
    public static <T> R<T> failure(int code, String message) {
        return new R<>(code, message);
    }

    // 返回一个带有自定义响应信息的失败响应对象，响应状态码为 500
    public static <T> R<T> failure(String message) {
        return new R<T>()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(message)
                .setData(null); // 将 data 字段设置为 null
    }


    // 返回一个默认的失败响应对象，响应状态码为 500，响应信息为 "操作失败"
    public static <T> R<T> failure() {
        return new R<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败");
    }
}
