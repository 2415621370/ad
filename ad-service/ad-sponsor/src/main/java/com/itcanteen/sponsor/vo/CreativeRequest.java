package com.itcanteen.sponsor.vo;

import com.itcanteen.sponsor.constant.CommonStatus;
import com.itcanteen.sponsor.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/4 11:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeRequest {


    private String name;


    private Integer type;

    private Integer materialType;


    private Integer height;


    private Integer width;


    private Long size;


    private Integer duration;


    private Integer auditStatus;


    private Long userId;


    private String url;

    public boolean createvalidate(){
        return !StringUtils.isEmpty(name)&&
                type!=null&&
                materialType!=null&&
                height!=null;
    }


    public Creative toEntity(){
        Creative creative = new Creative();
        creative.setName(name);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setCreateTime(new Date());
        creative.setDuration(duration);
        creative.setHeight(height);
        creative.setMaterialType(materialType);
        creative.setSize(size);
        creative.setType(type);
        creative.setUrl(url);
        creative.setWidth(width);
        return creative;
    }
}
