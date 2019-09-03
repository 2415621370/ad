package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.CommonStatus;
import com.itcanteen.sponsor.constant.Constants;
import com.itcanteen.sponsor.dao.AdPlanRepository;
import com.itcanteen.sponsor.dao.AdUserRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.entity.AdUser;
import com.itcanteen.sponsor.service.IPlanService;
import com.itcanteen.sponsor.utils.CommonUtils;
import com.itcanteen.sponsor.vo.AdPlanGetRequest;
import com.itcanteen.sponsor.vo.AdPlanGetResponse;
import com.itcanteen.sponsor.vo.AdPlanRequest;
import com.itcanteen.sponsor.vo.AdPlanResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 15:10
 */

@Service
public class PlanServiceImpl implements IPlanService {

    final
    AdUserRepository adUserRepository;

    final
    AdPlanRepository planRepository;

    @Autowired
    public PlanServiceImpl(AdPlanRepository planRepository, AdUserRepository adUserRepository) {
        this.planRepository = planRepository;
        this.adUserRepository = adUserRepository;
    }


    /**
     * 创建推广计划
     *
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        //验证传过来的参数是否为空
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        //判断关联userid是否存在
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_USER);
        }
        //判断该广告主下是否存在相同的广告计划
        AdPlan byUserIdAndPlanName = planRepository.findByUserIdAndPlanName(
                request.getUserId(),
                request.getPlanName());
        if (null != byUserIdAndPlanName) {
            throw new AdException(Constants.ErrorMsg.SAME_PLAN_NAME);
        }
        AdPlan adPlan = planRepository.save(
                new AdPlan(
                        request.getUserId(),
                        request.getPlanName(),
                        CommonUtils.parseStringToDate(request.getStartTime()),
                        CommonUtils.parseStringToDate(request.getEndTime())
                )
        );
        return new AdPlanResponse(
                adPlan.getId(),
                adPlan.getPlanName()
        );
    }


    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId());

    }

   @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws  AdException{
        if(!request.updateValidate()){
            throw  new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        AdPlan adPlan =
                planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(null==adPlan){
            throw  new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        adPlan.setPlanName(request.getPlanName());

        adPlan.setStartTime(
                CommonUtils.parseStringToDate(request.getStartTime())
        );
        adPlan.setEndTime(
                CommonUtils.parseStringToDate(request.getEndTime())
        );
        adPlan.setUpdateTime(new Date());
        AdPlan dbplan = planRepository.save(adPlan);

        return new AdPlanResponse(
                dbplan.getId(),
                dbplan.getPlanName()
        );


    }
    @Transactional
    public void deletePlan(AdPlanRequest request)
            throws AdException{
        if(!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        AdPlan adPlan =
                planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(null==adPlan){
            throw  new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }

        adPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adPlan.setUpdateTime(new Date());

        planRepository.save(adPlan);


    }





}
