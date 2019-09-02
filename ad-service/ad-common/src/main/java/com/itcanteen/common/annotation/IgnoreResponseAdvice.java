package com.itcanteen.common.annotation;

import java.lang.annotation.*;

/**
 *
 * 自定义注解
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 10:28
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreResponseAdvice {
}
