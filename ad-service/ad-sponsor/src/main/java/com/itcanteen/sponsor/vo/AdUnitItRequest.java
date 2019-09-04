package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 10:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitItRequest {

    private List<UnitIt>   unitIts;

    public boolean validate(){
        return !CollectionUtils.isEmpty(unitIts);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class UnitIt{
        private Long unitId;
        private String itTag;
    }
}
