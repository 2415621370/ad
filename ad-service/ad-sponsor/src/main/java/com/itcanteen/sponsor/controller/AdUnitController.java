package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.IAdUnitService;
import com.itcanteen.sponsor.vo.AdUnitRequest;
import com.itcanteen.sponsor.vo.AdUnitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody AdUnitRequest request)throws AdException {
        log.info("ad-sponsor:createUnit->{}",JSON.toJSONString(request));
        return unitService.createUnit(request);

    }
}
