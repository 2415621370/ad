package com.itcanteen.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 15:07
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchResponse {

    private Map<String,List<Creative>> adSolt2Ads = new HashMap<>();



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static  class Creative{

        private Long adId;
        private String adUrl;

        private Integer width;
        private Integer hight;

        private Integer  type;
        private Integer materialType;


        //展示监测url
        private List<String> showMonitorUrl = Arrays.asList("www.aaa.com","www.bbb.com");

        //点击监测url
        private List<String> clientMonitorUrl = Arrays.asList("www.ccc.com","www.ddd.com");


    }

}
