package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constants;
import com.itcanteen.sponsor.dao.AdPlanRepository;
import com.itcanteen.sponsor.dao.AdUnitRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.entity.AdUnit;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitKeyword;
import com.itcanteen.sponsor.service.IAdUnitService;
import com.itcanteen.sponsor.vo.AdUnitKeywordRequest;
import com.itcanteen.sponsor.vo.AdUnitKeywordResponse;
import com.itcanteen.sponsor.vo.AdUnitRequest;
import com.itcanteen.sponsor.vo.AdUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:13
 */

@Service
public class AdUnitServiceImpl implements IAdUnitService {


    final
    AdPlanRepository planRepository;

    final AdUnitKeywordRepository unitKeywordRepository;


    final
    AdUnitRepository unitRepository;

    @Autowired
    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository, AdUnitKeywordRepository unitKeywordRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
    }

    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request)
            throws AdException {

        //验证参数是否正确
        if (!request.createvalidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }
        //y验证传过来的planid是否存在
        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }

        AdUnit byPlanIdAndUnitName = unitRepository.
                findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());

        if (null != byPlanIdAndUnitName) {
            throw new AdException(Constants.ErrorMsg.SAME_UNIT_ERROR);
        }

        AdUnit unit = unitRepository.save(
                new AdUnit(
                        request.getPlanId(),
                        request.getUnitName(),
                        request.getPositionType(),
                        request.getBudget()
                )
        );

        return new AdUnitResponse(
                unit.getId(),
                unit.getUnitName()
        );
    }


    public AdUnitKeywordResponse createUnitKeyWords(AdUnitKeywordRequest request)
            throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<Long> unitIds = request.getUnitKeyWords().stream().map(
                AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

     /*   for (int i = 0; i < request.getUnitKeyWords().size(); i++) {


          //  request.getUnitKeyWords().get(i).getUnitId();
         //   request.getUnitKeyWords().get(i).getKeyWord();

            unitKeywordRepository.save(
                    new AdUnitKeyword(
                            request.getUnitKeyWords().get(i).getUnitId(),
                            request.getUnitKeyWords().get(i).getKeyWord()
                    )
            );
        }*/

       /* List list =   new ArrayList();

        request.getUnitKeyWords().forEach(i->{
            AdUnitKeyword unitKeyword =  new AdUnitKeyword(i.getUnitId(),i.getKeyWord());
            unitKeywordRepository.save(
                   // new AdUnitKeyword(i.getUnitId(),i.getKeyWord())
                    unitKeyword
            );
           Long id =  unitKeyword.getId();
            list.add(id);

        });


        AdUnitKeywordResponse adUnitKeywordResponse = new AdUnitKeywordResponse();
        adUnitKeywordResponse.setId(list);*/


        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> adUnitKeywordList = new ArrayList<>();

        request.getUnitKeyWords().forEach(
                i->{
                    adUnitKeywordList.add(
                            new AdUnitKeyword(
                                    i.getUnitId(),
                                    i.getKeyWord()
                            )
                    );
                }
        );

        ids =   unitKeywordRepository.saveAll(adUnitKeywordList).stream().map(
                AdUnitKeyword::getId
        ).collect(Collectors.toList());



        return new AdUnitKeywordResponse(ids);

    }


    public boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        return unitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }
}
