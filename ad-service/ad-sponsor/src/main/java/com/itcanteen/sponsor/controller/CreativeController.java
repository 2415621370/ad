package com.itcanteen.sponsor.controller;

import com.alibaba.fastjson.JSON;
import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.service.ICreativeService;
import com.itcanteen.sponsor.vo.CreativeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 11:49
 */

@RestController
@RequestMapping("/creative")
@Slf4j
public class CreativeController {

    @Autowired
    ICreativeService creativeService;


    @PostMapping("/createCretive")
    public CreativeResponse createCretive(
            @RequestBody CreativeRequest request
    ) throws AdException {

        log.info("ad-sponsor:createCretive->{}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
