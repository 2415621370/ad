package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.*;

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

    /**
     * 新增限制兴趣标签
     * @param request
     * @return
     * @throws AdException
     */
    public AdUnitItResponse creeteUnitIt(AdUnitItRequest request)
            throws AdException;

    /**
     * 新增地域的限制
     * @param request
     * @return
     * @throws AdException
     */
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException;


    /**
     * 创意与单元的中间表
     * @param request
     * @return
     * @throws AdException
     */
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;


}
