package com.itcanteen.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 15:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistrictFeature {

    private List<ProvinceAndCity> districts;


    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class  ProvinceAndCity{
        private String province;
        private String city;
    }
}
