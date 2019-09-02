package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 15:06
 */
public interface IPlanService {

    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;
}
