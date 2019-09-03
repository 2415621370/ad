package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 15:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {

    private Long id;

    private Long userId;


    private String planName;



    private String endTime;


    private String startTime;

    public boolean createValidate(){
        return userId!=null
                &&!StringUtils.isEmpty(planName)
                &&!StringUtils.isEmpty(startTime)
                &!StringUtils.isEmpty(endTime);
    }


    public boolean updateValidate(){
        return id!=null && userId!=null;
    }

    public boolean deleteValidate(){
        return id!=null && userId!=null;
    }
}
