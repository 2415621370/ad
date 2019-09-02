package com.itcanteen.common.advice;

import com.itcanteen.common.annotation.IgnoreResponseAdvice;
import com.itcanteen.common.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 10:25
 */

@RestControllerAdvice
@Slf4j
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        log.info("----------------------");

        if(   methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }

        if(methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        log.info("---------beforeBodyWrite-------------");

        CommonResponse<Object> objectCommonResponse = new CommonResponse<>(0,"");

        if(o==null){
            return objectCommonResponse;
        }else if(o instanceof  CommonResponse){
            objectCommonResponse =  ( CommonResponse<Object>)o;
        }else{
            objectCommonResponse.setData(o);
        }


        return objectCommonResponse;
    }
}
