package com.itcanteen.sponsor.entity;

import com.itcanteen.sponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 11:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit")
public class AdUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private Long id;

    @Column(name = "plan_id" ,nullable = false)
    private Long planId;


    @Column(name = "unit_name" ,nullable = false)
    private String unitName;

    @Column(name = "unit_status" ,nullable = false)
    private Integer unitStatus;

    @Column(name = "position_type" ,nullable = false)
    private Integer positionType;

    @Column(name = "budget" ,nullable = false)
    private Long budget;

    @Column(name = "create_time" ,nullable = false)
    private Date createTime;

    @Column(name = "update_time" ,nullable = false)
    private Date updateTime;


    public AdUnit(Long planId,String unitName,
                  Integer positionType,Long budget){

        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = new Date();

    }
}
