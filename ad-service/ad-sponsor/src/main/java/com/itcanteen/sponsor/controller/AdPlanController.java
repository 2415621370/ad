package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.common.vo.CommonResponse;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.service.IPlanService;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/getAdPlanByIds")
   public List<AdPlan> getAdPlanByIds(
          @RequestBody AdPlanGetRequest request) throws AdException {

       log.info("ad-sponsor:getAdPlanByIds->{}", JSON.toJSONString(request));
        return planService.getAdPlanByIds(request);
   }


    @PostMapping("/updatePlan")
   public AdPlanResponse updatePlan(
           @RequestBody AdPlanRequest request) throws AdException{
       log.info("ad-sponsor:updatePlan->{}", JSON.toJSONString(request));
       return planService.updateAdPlan(request);
   }


    @PostMapping("/deletePlan")
   public void deletePlan(
           @RequestBody AdPlanRequest request) throws  AdException{
        log.info("ad-sponsor:deletePlan->{}", JSON.toJSONString(request));
         planService.deletePlan(request);
   }
}
