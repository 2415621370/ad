package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 11:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {

    public List<CreativeUnitItem> creativeUnitItemList;

    public Boolean validate(){
        return !CollectionUtils.isEmpty(creativeUnitItemList);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class CreativeUnitItem{
        private Long creativeId;
        private Long unitId;
    }
}
