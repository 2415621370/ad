package com.itcanteen.sponsor.dao;

import com.itcanteen.sponsor.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:03
 */
public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {

    AdUnit  findByPlanIdAndUnitName(Long planId,String unitName);
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);


}
