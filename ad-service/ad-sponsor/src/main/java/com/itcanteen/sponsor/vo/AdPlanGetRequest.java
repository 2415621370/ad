package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 10:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    private  Long userId;
    private List<Long> ids;

    public boolean validate(){
        return userId!=null &&!CollectionUtils.isEmpty(ids);
    }



}
