package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.CreativeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 11:36
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request)  throws AdException;
}
