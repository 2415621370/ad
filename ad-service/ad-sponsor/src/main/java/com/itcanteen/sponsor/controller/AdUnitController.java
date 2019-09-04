package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IAdUnitService;
import com.itcanteen.sponsor.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:27
 */
@RestController
@Slf4j
@RequestMapping("adunit")
public class AdUnitController {

    final
    IAdUnitService unitService;

    @Autowired
    public AdUnitController(IAdUnitService unitService) {
        this.unitService = unitService;
    }


    @PostMapping("/createUnit")
    public AdUnitResponse createUnit(
            @RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor:createUnit->{}", JSON.toJSONString(request));
        return unitService.createUnit(request);

    }


    @PostMapping("/createUnitKeyWord")
    public AdUnitKeywordResponse createUnitKeyWord(
            @RequestBody AdUnitKeywordRequest request
    ) throws AdException {
        log.info("ad-sponsor:createUnitKeyWord->{}", JSON.toJSONString(request));
        return unitService.createUnitKeyWords(request);
    }


    @PostMapping("/createUnitIt")
    public AdUnitItResponse createUnitIt(
            @RequestBody AdUnitItRequest request
    ) throws AdException {

        log.info("ad-sponsor:createUnitIt->{}", JSON.toJSONString(request));
        return unitService.creeteUnitIt(request);
    }


    @PostMapping("/createUnitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(
            @RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor:createUnitDistrict->{}",
                JSON.toJSONString(request));
        return unitService.createUnitDistrict(request);

    }

    @PostMapping("/createCretiveUnit")
    public CreativeUnitResponse createCretiveUnit(
            @RequestBody CreativeUnitRequest request
    ) throws AdException {
        log.info("ad-sponsor:createCretiveUnit->{}",
                JSON.toJSONString(request));
        return unitService.createCreativeUnit(request);
    }

    public static void main(String[] args) {

        AdUnitKeywordRequest.UnitKeyword unitKeyWords = new AdUnitKeywordRequest.UnitKeyword();
        unitKeyWords.setKeyWord("tiyu");
        unitKeyWords.setUnitId(10L);


        List list = new ArrayList<>();
        list.add(unitKeyWords);
        AdUnitKeywordRequest request = new AdUnitKeywordRequest();
        request.setUnitKeyWords(list);
        // response.setId(list);

        System.out.print(JSON.toJSONString(request));
    }
}
