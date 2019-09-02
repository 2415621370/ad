package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IUserService;
import com.itcanteen.sponsor.vo.CreateUserRequest;
import com.itcanteen.sponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 14:20
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 创建广告主（注册）
     * @param request
     * @return
     * @throws AdException
     */
    //@RequestMapping(value = "/createUser",method = RequestMethod.POST)
    @PostMapping("/create")
    public CreateUserResponse createUser(
            @RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor:createUser->{}", JSON.toJSONString(request));
       return  userService.careteUser(request);

    }
}
