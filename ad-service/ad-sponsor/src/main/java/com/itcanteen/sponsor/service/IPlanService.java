package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 15:06
 */
public interface IPlanService {

    public AdPlanResponse createAdPlan(AdPlanRequest request)
            throws AdException;

    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException;

    public AdPlanResponse updateAdPlan(AdPlanRequest request)
            throws  AdException;

    public void deletePlan(AdPlanRequest request)
            throws AdException;
}
