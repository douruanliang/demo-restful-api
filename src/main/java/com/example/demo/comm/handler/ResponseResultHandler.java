package com.example.demo.comm.handler;


import com.example.demo.comm.anno.ResponseResult;
import com.example.demo.comm.enums.ApiStyleEnum;
import com.example.demo.comm.interceptor.ResponseResultInterceptor;
import com.example.demo.comm.result.DefaultErrorResult;
import com.example.demo.comm.result.PlatformResult;
import com.example.demo.comm.result.Result;
import com.example.demo.comm.utils.JsonUtil;
import com.example.demo.comm.utils.RequestContextUtil;
import com.example.demo.comm.web.constants.HeaderConstants;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口响应体处理器
 */
@ControllerAdvice
public class ResponseResultHandler  implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        HttpServletRequest request = RequestContextUtil.getRequest();
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        return responseResultAnn != null && !ApiStyleEnum.NONE.name().equalsIgnoreCase(request.getHeader(HeaderConstants.API_STYLE));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextUtil.getRequest().getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);

        Class<? extends Result> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return PlatformResult.builder()
                        .code(defaultErrorResult.getCode())
                        .message(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getErrors())
                        .build();
            } else if (body instanceof String) {
                return JsonUtil.object2Json(PlatformResult.success(body));
            }

            return PlatformResult.success(body);
        }

        return body;
    }
}
