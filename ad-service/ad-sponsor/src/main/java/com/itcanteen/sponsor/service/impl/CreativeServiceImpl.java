package com.itcanteen.sponsor.service.impl;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constants;
import com.itcanteen.sponsor.dao.CreativeRepository;
import com.itcanteen.sponsor.entity.Creative;
import com.itcanteen.sponsor.service.ICreativeService;
import com.itcanteen.sponsor.vo.CreativeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 11:39
 */

@Service
public class CreativeServiceImpl implements ICreativeService {

    final
    CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException{

       if(!request.createvalidate()){
           throw new AdException(Constants.ErrorMsg.REQUEST_PARAMS_ERROR);
       }


        Creative creative = creativeRepository.save(
                request.toEntity()
        );


        return new CreativeResponse(
                creative.getId(),
                creative.getName()
        );
    }
}
