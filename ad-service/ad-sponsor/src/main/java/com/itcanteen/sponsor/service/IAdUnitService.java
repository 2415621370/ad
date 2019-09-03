package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.AdUnitKeywordRequest;
import com.itcanteen.sponsor.vo.AdUnitKeywordResponse;
import com.itcanteen.sponsor.vo.AdUnitRequest;
import com.itcanteen.sponsor.vo.AdUnitResponse;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:10
 */
public interface IAdUnitService  {

    /**
     * 创建推广单元
     * @param request
     * @return
     * @throws AdException
     */
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    /**
     *新增限制关键词
     */

    AdUnitKeywordResponse createUnitKeyWords(AdUnitKeywordRequest request) throws AdException;


}
