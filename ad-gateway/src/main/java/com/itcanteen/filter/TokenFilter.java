package com.itcanteen.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.ResponseContent;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/5 16:26
 */

@Slf4j
@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 从request里面获取oken
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        System.out.print("=======");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        StringBuffer requestURL = request.getRequestURL();

        log.info("requestURL-{}",requestURL);
        String requestURI = request.getRequestURI();
        log.info("requestURI-{}",requestURI);
        HttpServletResponse response = currentContext.getResponse();

        response.setCharacterEncoding("UTF-8");

        if(!requestURI.contains("/user/create")){
            String token =  request.getParameter("token");

            if(StringUtils.isEmpty(token)){
                log.info("token 不能为空");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(400);
                currentContext.setResponseBody("token 不能为空");
                return null;
            }

            try{
                Jwts.parser().setSigningKey("itcanteen").parseClaimsJws(token).getBody();
            }catch(io.jsonwebtoken.ExpiredJwtException e){
                log.info("token 已失效");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(400);
                currentContext.setResponseBody("token 已失效");
                return null;
            }catch ( io.jsonwebtoken.SignatureException se){
                log.info("token 不正确");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(400);
                currentContext.setResponseBody("token 不正确");
                return null;
            }catch (Exception e){
                log.info("token 不正确");
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(400);
                currentContext.setResponseBody("token解析错误");
                return null;
            }
        }






        return null;
    }
}
