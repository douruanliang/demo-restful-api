package com.example.demo.comm.interceptor;

import com.example.demo.comm.enums.CallSourceEnum;
import com.example.demo.comm.enums.ResultCode;
import com.example.demo.comm.exceptions.BusinessException;
import com.example.demo.comm.utils.StringUtil;
import com.example.demo.comm.web.constants.HeaderConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class HeaderParamsCheckInterceptor implements HandlerInterceptor {


    /**
     * 收集更多的调用信息，以便我们做后续业务处理、日志记录等等的一些
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
            String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
            String appVersion = request.getHeader(HeaderConstants.APP_VERSION);

            if (StringUtil.isAnyBlank(callSource, apiVersion,appVersion)) {
                throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
            }

            /*try {
             个人觉得没有什么意思
                Double.valueOf(apiVersion);
            } catch (NumberFormatException e) {
                throw new BusinessException(ResultCode.PARAM_IS_INVALID);
            }
*/
            /*if ((CallSourceEnum.ANDROID.name().equals(callSource) ||
                    CallSourceEnum.IOS.name().equals(callSource)) && StringUtil.isEmpty(appVersion)) {

                throw new BusinessException(ResultCode.PARAM_NOT_COMPLETE);
            }*/

            /*if (!CallSourceEnum.isValid(callSource)) {
                throw new BusinessException(ResultCode.PARAM_IS_INVALID);
            }*/

        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
