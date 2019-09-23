package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdSlot {

    //广告位编码
    private String adSlotCode;
   //流量类型
    private Integer positionType;

    //宽和高
    private Integer width;
    private Integer height;

    //最低出价
    private Integer minCpm;

    //广告物料类型：图片，视频
    private List<Integer> type;

}
