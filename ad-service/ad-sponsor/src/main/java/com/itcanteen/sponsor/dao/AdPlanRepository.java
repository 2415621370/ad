package com.itcanteen.sponsor.dao;

import com.itcanteen.sponsor.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:31
 */
public interface AdPlanRepository extends JpaRepository<AdPlan,Long> {

    AdPlan findByIdAndUserId(Long id,Long userId);

    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);


    AdPlan findByUserIdAndPlanName(Long userId,String palnName);

    List<AdPlan> findAllByPlanStatus(Integer status);

}
