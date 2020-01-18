package com.example.demo.comm.result;


import com.example.demo.comm.enums.BusinessExceptionEnum;
import com.example.demo.comm.enums.ResultCode;
import com.example.demo.comm.exceptions.BusinessException;
import com.example.demo.comm.utils.RequestContextUtil;
import com.example.demo.comm.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * 默认全局错误返回格式
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultErrorResult implements Result {

    /**
     * HTTP 响应状态码{@link org.springframework.http.HttpStatus}
     */
    private Integer status;

    /**
     * HTTP响应状态码的英文提示
     */
    private String error;

    /**
     * 异常堆栈的精简信息
     */
    private String message;

    /**
     * 我们系统内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
     * <p>
     * 备注：spring boot默认返回异常时，该字段为null
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;


    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = DefaultErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        String path = RequestContextUtil.getRequest().getRequestURI();
        result.setPath(path);
        result.setTimestamp(new Date());
        return result;
    }

    public static DefaultErrorResult failure(BusinessException e) {
        BusinessExceptionEnum ee = BusinessExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return DefaultErrorResult.failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
        }

        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e.getResultCode() == null ? ResultCode.SUCCESS : e.getResultCode(), e, HttpStatus.OK, e.getData());
        if (StringUtil.isNotEmpty(e.getMessage())) {
            defaultErrorResult.setMessage(e.getMessage());
        }
        return defaultErrorResult;
    }
}
