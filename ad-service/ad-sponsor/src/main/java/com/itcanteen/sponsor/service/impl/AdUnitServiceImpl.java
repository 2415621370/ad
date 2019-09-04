package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constants;
import com.itcanteen.sponsor.dao.AdPlanRepository;
import com.itcanteen.sponsor.dao.AdUnitRepository;
import com.itcanteen.sponsor.dao.CreativeRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitDistrictRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitItRepository;
import com.itcanteen.sponsor.dao.unit_condition.AdUnitKeywordRepository;
import com.itcanteen.sponsor.dao.unit_condition.CreativeUnitRepository;
import com.itcanteen.sponsor.entity.AdPlan;
import com.itcanteen.sponsor.entity.AdUnit;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitDistrict;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitIt;
import com.itcanteen.sponsor.entity.unit_condition.AdUnitKeyword;
import com.itcanteen.sponsor.entity.unit_condition.CreativeUnit;
import com.itcanteen.sponsor.service.IAdUnitService;
import com.itcanteen.sponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional
public class AdUnitServiceImpl implements IAdUnitService {


    private final CreativeUnitRepository creativeUnitRepository;


    final
    CreativeRepository creativeRepository;


    final
    AdUnitItRepository unitItRepository;


    final
    AdPlanRepository planRepository;

    final
    AdUnitKeywordRepository unitKeywordRepository;

    final
    AdUnitDistrictRepository unitDistrictRepository;


    final
    AdUnitRepository unitRepository;

    @Autowired
    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository, AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository, AdUnitDistrictRepository unitDistrictRepository, CreativeRepository creativeRepository, CreativeUnitRepository creativeUnitRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest request)
            throws AdException {

        //验证参数是否正确
        if (!request.createvalidate()) {
            log.info("---------------");
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
            log.info("--------");
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<Long> unitIds = request.getUnitKeyWords().stream().map(
                AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());

        log.info("unitIds->{}", unitIds);
        if (!isRelatedUnitExist(unitIds)) {
            log.info("---------yyyyyyyyyyy--------");
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
                i -> {
                    adUnitKeywordList.add(
                            new AdUnitKeyword(
                                    i.getUnitId(),
                                    i.getKeyWord()
                            )
                    );
                }
        );
        ids = unitKeywordRepository.saveAll(adUnitKeywordList).stream().map(
                AdUnitKeyword::getId
        ).collect(Collectors.toList());
        return new AdUnitKeywordResponse(ids);

    }


    public AdUnitItResponse creeteUnitIt(AdUnitItRequest request)
            throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<Long> unitIds = request.getUnitIts().stream().map(
                AdUnitItRequest.UnitIt::getUnitId
        ).collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List<Long> ids = Collections.emptyList();
        ArrayList<AdUnitIt> adUnitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> adUnitIts.
                add(new AdUnitIt(i.getUnitId(), i.getItTag())));

        ids = unitItRepository.saveAll(adUnitIts)
                .stream()
                .map(AdUnitIt::getId).collect(Collectors.toList());
        return new AdUnitItResponse(ids);


    }


    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List unitIds = request.getUnitDistrictList().stream().map(
                AdUnitDistrictRequest.UnitDistrict::getUnitId
        ).collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }


        ArrayList<AdUnitDistrict> adUnitDistricts = new ArrayList<>();

        request.getUnitDistrictList().forEach(
                i -> adUnitDistricts.add(new AdUnitDistrict(i.getUnitId(), i.getProvince(), i.getCity()))
        );

        List ids = unitDistrictRepository.saveAll(adUnitDistricts).stream().map(
                AdUnitDistrict::getId
        ).collect(Collectors.toList());

        return new AdUnitDistrictResponse(ids);


    }


    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        List unitIds = request.getCreativeUnitItemList().stream().map(
                CreativeUnitRequest.CreativeUnitItem::getUnitId
        ).collect(Collectors.toList());

        List creativeIds = request.getCreativeUnitItemList().stream().map(
                CreativeUnitRequest.CreativeUnitItem::getCreativeId
        ).collect(Collectors.toList());


        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        if (!isRelatedCreativeExist(creativeIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
        }

        ArrayList<CreativeUnit> creativeUnits = new ArrayList<>();

        request.getCreativeUnitItemList().forEach(
                i -> creativeUnits.add(
                        new CreativeUnit(i.getCreativeId(), i.getUnitId())
                )
        );


        List ids = creativeUnitRepository.saveAll(creativeUnits).stream().map(
                CreativeUnit::getId
        ).collect(Collectors.toList());

        return new CreativeUnitResponse(ids);


    }


    public boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        return unitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();
    }

    public boolean isRelatedCreativeExist(List<Long> creativeIds) {
        if (CollectionUtils.isEmpty(creativeIds)) {
            return false;
        }

        return creativeRepository.findAllById(creativeIds).size() ==
                new HashSet<>(creativeIds).size();
    }
}
