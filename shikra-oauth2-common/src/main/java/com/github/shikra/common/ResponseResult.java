package com.github.shikra.common;


/**
 * 公共响应类
 *
 * @param <T>
 */
public class ResponseResult<T> {

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_FAIL = 500;

    private static final int CODE_ERROR = 500;

    private static final int CODE_NO_LOGIN = 300;

    private int code;

    private String message;

    private T data;

    public ResponseResult() {

    }

    public ResponseResult(int code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>(CODE_SUCCESS, "success", null);
    }

    public static <T> ResponseResult<T> success(String message) {
        return new ResponseResult<T>(CODE_SUCCESS, message, null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(CODE_SUCCESS, "success", data);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<T>(CODE_SUCCESS, message, data);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<T>(CODE_ERROR, "fail", null);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<T>(CODE_ERROR, message, null);
    }

    public static <T> ResponseResult<T> error(T data) {
        return new ResponseResult<T>(CODE_ERROR, "fail", data);
    }

    public static <T> ResponseResult<T> error(String message, T data) {
        return new ResponseResult<T>(CODE_ERROR, message, data);
    }

    public static <T> ResponseResult<T> badrequest() {
        return new ResponseResult<T>(CODE_FAIL, "no identifier arguments", null);
    }

    public static <T> ResponseResult<T> badrequest(String message) {
        return new ResponseResult<T>(CODE_FAIL, message, null);
    }

    public static <T> ResponseResult<T> badrequest(T data) {
        return new ResponseResult<T>(CODE_FAIL, "no identifier arguments", data);
    }

    public static <T> ResponseResult<T> badrequest(String message, T data) {
        return new ResponseResult<T>(CODE_FAIL, message, data);
    }

    public static <T> ResponseResult<T> noLogin(String message) {
        return new ResponseResult<T>(CODE_NO_LOGIN, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
