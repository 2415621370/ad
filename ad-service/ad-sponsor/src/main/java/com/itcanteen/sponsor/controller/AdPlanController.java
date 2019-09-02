package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IPlanService;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 15:35
 */

@RestController
@RequestMapping("/plan")
@Slf4j
public class AdPlanController {

    final
    IPlanService planService;

    @Autowired
    public AdPlanController(IPlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/create")
    public AdPlanResponse createPlan(
         @RequestBody AdPlanRequest request) throws AdException {

        log.info("ad-sponsor:createPlan->{}", JSON.toJSONString(request));
        return planService.createAdPlan(request);
    }
}
