package com.itcanteen.common.advice;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.common.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * 只有任何一个类上有注解@RestController，这个类就会处理异常，
 * 但是异常的类型必须是AdException.class，
 * 然后返回CommonResponse 对象
 *
 *
 * 增强-》干预
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/8/31 10:50
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(AdException exception){
        log.info("handlerAdException");
        CommonResponse<String> response = new CommonResponse<>(-1, "error");
        response.setData(exception.getMessage());
        return response;


    }
}
