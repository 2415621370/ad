package com.itcanteen.search.vo;

import com.itcanteen.search.vo.feature.DistrictFeature;
import com.itcanteen.search.vo.feature.FeatureRelation;
import com.itcanteen.search.vo.feature.ItFeature;
import com.itcanteen.search.vo.feature.KeyWordFeature;
import com.itcanteen.search.vo.media.AdSlot;
import com.itcanteen.search.vo.media.App;
import com.itcanteen.search.vo.media.Device;
import com.itcanteen.search.vo.media.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    //媒体方的请求标识
    private String mediaId;

    //请求基本信息
    private RequestInfo requestInfo;

    //匹配基本信息
    private FeatureInfo featureInfo;



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static  class FeatureInfo{

        private KeyWordFeature keyWordFeature;

        private DistrictFeature districtFeature;

        private ItFeature itFeature;

        private FeatureRelation relation = FeatureRelation.AND;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo{


        //唯一请求id
        private String requestId;

        //广告位信息
        private List<AdSlot> adSlots;

        //终端信息
        private App app;


        //地域信息
        private Geo geo;

        //设备信息
        private Device device;









    }


}
