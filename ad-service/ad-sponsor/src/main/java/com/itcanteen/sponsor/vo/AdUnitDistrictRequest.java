package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 10:48
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistrictList;

    public  boolean validate(){
        return !CollectionUtils.isEmpty(unitDistrictList);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class UnitDistrict{
        private Long unitId;

        private String province;
        private String city;
    }
}
