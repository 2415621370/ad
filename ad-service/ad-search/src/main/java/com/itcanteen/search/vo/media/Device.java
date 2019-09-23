package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:44
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {


    // 设备 id
    private String deviceCode;

    // mac
    private String mac;

    // ip
    private String ip;

    // 机型编码
    private String model;
    // 分辨率尺寸
    private String diaplaySize;

    // 屏幕尺寸
    private String screenSize;

    // 设备序列号
    private String serialName;


}
