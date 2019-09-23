package com.itcanteen.search;

import com.itcanteen.common.annotation.IgnoreResponseAdvice;
import com.itcanteen.search.vo.SearchRequest;
import com.itcanteen.search.vo.SearchResponse;
import com.itcanteen.search.vo.feature.DistrictFeature;
import com.itcanteen.search.vo.feature.FeatureRelation;
import com.itcanteen.search.vo.feature.ItFeature;
import com.itcanteen.search.vo.feature.KeyWordFeature;
import com.itcanteen.search.vo.media.AdSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/19 9:42
 */


@Controller
public class SearchController {


    @Autowired(required = false)
    SearchService searchService;



    @RequestMapping("/search/ads")
    @ResponseBody
    @IgnoreResponseAdvice
    public SearchResponse fetchAds(@RequestBody(required = false) SearchRequest request,
                                   @RequestParam  String flag){


        if(flag.equals("false")){
            //请求的广告位的信息
            List<AdSlot> adSlots = request.getRequestInfo().getAdSlots();

            //关键词的过滤
            KeyWordFeature keyWordFeature = request.getFeatureInfo().getKeyWordFeature();

            FeatureRelation relation = request.getFeatureInfo().getRelation();

            //关键词列表
            List<String> keywords = keyWordFeature.getKeywords();

            ArrayList<Long> keywordsUnitIds = new ArrayList<>();


            //拿到关键字UNitid
            keywords.forEach(i->{
                List<Map<String, Object>> maps = searchService.searchByKeyword(i);
                for (int i1 = 0; i1 < maps.size(); i1++) {
                    Long unit_id = (Long)maps.get(i1).get("unit_id");
                    keywordsUnitIds.add(unit_id);
                }
            });

            //地域信息的过滤
            DistrictFeature districtFeature = request.getFeatureInfo().getDistrictFeature();
            List<DistrictFeature.ProvinceAndCity> districts = districtFeature.getDistricts();

            ArrayList<Long> districtUnitIds = new ArrayList<>();
            districts.forEach(i->{
                String city = i.getCity();
                String province = i.getProvince();
                List<Map<String, Object>> maps = searchService.searchByDistrict(province, city);
                for (int i1 = 0; i1 < maps.size(); i1++) {
                    Long unit_id =(Long) maps.get(i1).get("unit_id");
                    districtUnitIds.add(unit_id);
                }

            });



            //兴趣信息的过滤
            ItFeature itFeature =
                    request.getFeatureInfo().getItFeature();

            List<String> its = itFeature.getIts();



            // 构造响应对象
            SearchResponse response = new SearchResponse();
            Map<String, List<SearchResponse.Creative>> adSlot2Ads =
                    response.getAdSolt2Ads();

            List<SearchResponse.Creative> creativeListResponse = new ArrayList<>();

            ArrayList<Long> itUnitIds = new ArrayList<>();
            its.forEach(i->{
                List<Map<String, Object>> maps = searchService.searchByits(i);
                for (int i1 = 0; i1 < maps.size(); i1++) {
                    Long unit_id =(Long) maps.get(i1).get("unit_id");
                    itUnitIds.add(unit_id);
                }
            });



            for(AdSlot adSlot:adSlots){
                //根据流量类型获取AdUnit
                Integer positionType = adSlot.getPositionType();

                //
                List<Map<String, Object>> adUnitList = searchService.searchAdUnitByPositionType(positionType);


                List<Long> unitIds = new ArrayList<>();

                for (int i = 0; i < adUnitList.size(); i++) {
                    Long id = (Long)adUnitList.get(i).get("id");
                    unitIds.add(id);
                }
                //
                HashSet<Long> unitSetIds = new HashSet<>(unitIds);


                HashSet<Long> targetUnitIdSet ;
                if(relation==FeatureRelation.AND){
                    Collection<Long> intersection = CollectionUtils.intersection(keywordsUnitIds, districtUnitIds);
                    Collection<Long> intersection1 = CollectionUtils.intersection(itUnitIds, unitIds);
                    Collection<Long> intersection2 = CollectionUtils.intersection(intersection, intersection1);
                    targetUnitIdSet = new HashSet<>(intersection2);
                }else {
                    Collection<Long> union = CollectionUtils.union(keywordsUnitIds, districtUnitIds);
                    Collection<Long> union1 = CollectionUtils.union(itUnitIds, unitIds);
                    Collection<Long> union2 = CollectionUtils.union(union, union1);
                    targetUnitIdSet = new HashSet<>(union2);
                }


                List<Map<String, Object>> newList = new ArrayList<>();



                //只要有效的
                targetUnitIdSet.forEach(i->{
                    List<Map<String, Object>> maps = searchService.searchAdUnitByUnitId(i);
                    for (int i1 = 0; i1 < maps.size(); i1++) {
                        Integer unit_status = (Integer)maps.get(i1).get("unit_status");
                        if(unit_status==1){//有效
                            newList.add(maps.get(i1));
                        }
                    }
                });


                //获取单元与创意的中间表
                List<Map<String, Object>> creativeUnitIds = new ArrayList<>();

                newList.forEach(i->{
                    Long unit_id = (Long)i.get("unit_id");
                    List<Map<String, Object>> maps = searchService.searchAdids(unit_id);
                    for (int i1 = 0; i1 < maps.size(); i1++) {
                        creativeUnitIds.add(maps.get(i1));
                    }

                });





                //再匹配高与宽的信息：略

                //List<Map<String, Object>> creativeList = null;

                creativeUnitIds.forEach(i->{
                    Long creative_id = (Long)i.get("creative_id");
                    List<Map<String, Object>>  creativeList =
                            searchService.searchCreative(creative_id);

                    for (int i1 = 0; i1 < creativeList.size(); i1++) {
                        SearchResponse.Creative creative = new SearchResponse.Creative();
                        creative.setAdId( (Long)creativeList.get(i1).get("id"));
                        creative.setAdUrl((String)creativeList.get(i1).get("url"));
                        creative.setMaterialType((Integer) creativeList.get(i1).get("material_type"));
                        creative.setHight((Integer) creativeList.get(i1).get("hight"));
                        creative.setWidth((Integer) creativeList.get(i1).get("width"));
                        creativeListResponse.add(creative);
                    }

                });


                adSlot2Ads.put(adSlot.getAdSlotCode(),creativeListResponse);
                response.setAdSolt2Ads(adSlot2Ads);

            }

            return response;
        }else{

            // 构造响应对象
            SearchResponse response = new SearchResponse();
            Map<String, List<SearchResponse.Creative>> adSlot2Ads =
                    response.getAdSolt2Ads();

            ArrayList<SearchResponse.Creative> arrayList = new ArrayList<>();


            SearchResponse.Creative creative1 = new SearchResponse.Creative();
            creative1.setWidth(100);
            creative1.setHight(200);
            creative1.setAdUrl("https://baimugudu.oss-cn-beijing.aliyuncs.com/data/QQ20190923-0.jpg");
            creative1.setMaterialType(01);
            creative1.setAdId(1L);
            creative1.setType(001);

            SearchResponse.Creative creative2 = new SearchResponse.Creative();
            creative2.setWidth(100);
            creative2.setHight(200);
            creative2.setMaterialType(02);
            creative2.setAdUrl("http://www.bwie.net/templets/default/images/logo.gif");
            creative2.setAdId(2L);
            creative2.setType(002);

            arrayList.add(creative1);
            arrayList.add(creative2);

            adSlot2Ads.put("001",arrayList);


            ArrayList<SearchResponse.Creative> arrayList1 = new ArrayList<>();


            SearchResponse.Creative creative = new SearchResponse.Creative();
            creative.setWidth(900);
            creative.setHight(800);
            creative.setMaterialType(02);
            creative.setAdUrl("http://172.16.10.119:8080/bwie/images/image/banner1.png");
            creative.setAdId(10L);
            creative.setType(003);

            arrayList1.add(creative);

            adSlot2Ads.put("003",arrayList1);
            response.setAdSolt2Ads(adSlot2Ads);

            return response;
        }








    }
}
