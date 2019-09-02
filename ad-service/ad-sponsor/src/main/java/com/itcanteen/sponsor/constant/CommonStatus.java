package com.itcanteen.sponsor.constant;

import lombok.Getter;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 14:06
 */


@Getter
public enum CommonStatus {

    VALID(1,"有效状态"),
    INVALID(0,"无效状态");


    private Integer status;
    private String desc;

    CommonStatus(Integer status,String desc){
        this.desc = desc;
        this.status = status;
    }


}
