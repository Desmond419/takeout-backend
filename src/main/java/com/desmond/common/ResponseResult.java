package com.desmond.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg = "";
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(final Integer code) {
        this.code = code;
    }

    public ResponseResult(final Integer code, final T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(final Integer code, final String msg, final T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpStatus.OK.value(), data);
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>(HttpStatus.OK.value(), msg);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(HttpStatus.OK.value(), msg, data);
    }

    public static <T> ResponseResult<T> success(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> ResponseResult<T> fail(Integer code, String msg) {
        return new ResponseResult<>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
