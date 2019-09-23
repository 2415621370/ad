package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/18 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App {

    //应用编码
    private String appCode;


    //应用的名字
    private String appName;

    //应用的包名
    private String packageName;


    //activity名称
    private String activityName;

}
