package com.itcanteen.sponsor.dao;

import com.itcanteen.sponsor.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:25
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {


    /**
     * 根据用户名查找用户
     */

    AdUser findByUsername(String username);
}
