package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constants;
import com.itcanteen.sponsor.dao.AdUserRepository;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.service.IUserService;
import com.itcanteen.sponsor.utils.CommonUtils;
import com.itcanteen.sponsor.vo.CreateUserRequest;
import com.itcanteen.sponsor.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:29
 */


@Service
@Slf4j
public class UserServiceImpl implements IUserService {


    final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AdUser findByUsername(String username) {
        return null;
    }

    @Override
    @Transactional
    public CreateUserResponse careteUser(CreateUserRequest userRequest) throws AdException{

        //验证传过来的userRequest 对象中的username属性是否为空
        if(!userRequest.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        //判断当前数据库里面是否相同的用户名
        AdUser byUsername = userRepository.findByUsername(userRequest.getUsername());
        if(null!=byUsername){
            throw  new AdException(Constants.ErrorMsg.SAME_USER_ERROR);
        }
        //保存
        AdUser adUser = userRepository.save(new AdUser(
                userRequest.getUsername(),
              //  CommonUtils.md5(userRequest.getUsername())
               // CommonUtils.jwtSign()
                "token"
                )
        );

       Long userId =  adUser.getId();
       String token =  CommonUtils.jwtSign(userId+"",adUser.getUsername());
       log.info("token:{}",token);
        adUser.setToken(token);
        AdUser dbUser = userRepository.save(adUser);

        return new CreateUserResponse(
                dbUser.getId(),
                dbUser.getUsername(),
                dbUser.getToken(),
                dbUser.getCreateTime(),
                dbUser.getUpdateTime()
        );
    }
}
