package com.itcanteen.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 17:45
 *
 * 声明式服务调用 feign
 */

@FeignClient(name="AD-KAFKA-ES")
public interface SearchService {

    @RequestMapping("/seacrh/keyword")
    public List<Map<String, Object>> searchByKeyword(String keyword);


    @RequestMapping("/searchAdUnitByPositionType")
    public  List<Map<String, Object>> searchAdUnitByPositionType(Integer positionType);

    @RequestMapping("/searchByDistrict")
    public List<Map<String, Object>> searchByDistrict(@RequestParam("provice") String provice, @RequestParam("city") String city);


    @RequestMapping("/searchByits")
    public List<Map<String, Object>> searchByits(String its);

    @RequestMapping("/searchAdUnitByUnitId")
    public List<Map<String, Object>> searchAdUnitByUnitId(Long unitId);

    @RequestMapping("/searchAdids")
    public List<Map<String, Object>> searchAdids(Long unitId);


    @RequestMapping("/searchCreative")
    public List<Map<String, Object>> searchCreative(Long creativeId);
}
