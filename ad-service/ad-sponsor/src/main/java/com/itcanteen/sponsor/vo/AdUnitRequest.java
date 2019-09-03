package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;
    private Integer positionType;
    private Long budget;


    public boolean createvalidate(){
        return planId!=null
                && !StringUtils.isEmpty(unitName)
                && positionType!=null
                && budget!=null;
    }
}
