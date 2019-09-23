package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:33
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Geo {

    //纬度
    private Float latitude;

    //经度
    private Float longitude;


    //省市
    private String city;
    private String province;
}
