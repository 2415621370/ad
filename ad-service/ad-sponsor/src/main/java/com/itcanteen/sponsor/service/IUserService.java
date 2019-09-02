package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.vo.CreateUserRequest;
import com.itcanteen.sponsor.vo.CreateUserResponse;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:28
 */
public interface IUserService {

    AdUser findByUsername(String username);

    CreateUserResponse careteUser(CreateUserRequest userRequest) throws AdException;
}
