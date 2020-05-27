package com.github.shikra.server.exception;

import com.github.shikra.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error("请求参数为空", e);
        return ResponseResult.badrequest("请求参数 " + e.getParameterName() + " 不能为空");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.error("不支持的请求方法", ex);
        return ResponseResult.badrequest("不支持的请求方法");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error("", e);
        return ResponseResult.badrequest("POST请求参数有误");
    }

    /**
     * 输入参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult NotValidExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
        log.error("无效的请求参数", e);

        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return ResponseResult.badrequest(fieldError.getDefaultMessage());
            }
        }
        return ResponseResult.badrequest("无效的请求参数");
    }

    /**
     * 404异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseResult NoHandlerFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("访问的地址不存在", e);
        return ResponseResult.badrequest("访问的地址不存在");
    }

    /**
     * 默认异常处理，前面未处理
     */
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult defaultHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("服务器内部错误", e);
        return ResponseResult.badrequest("服务器内部错误");
    }
}
