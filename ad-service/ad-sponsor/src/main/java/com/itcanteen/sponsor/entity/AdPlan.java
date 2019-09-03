package com.itcanteen.sponsor.entity;

import com.itcanteen.sponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 *  推广计划表
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_plan")
public class AdPlan {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "plan_name",nullable = false)
    private String planName;


    @Column(name = "plan_status",nullable = false)
    private Integer planStatus;

    @Column(name = "start_date",nullable = false)
    private Date startTime;

    @Column(name = "end_date",nullable = false)
    private Date endTime;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;


    public AdPlan(Long userId,String planName,
                  Date startTime,Date endTime){
        this.userId = userId;
        this.createTime = new Date();
        this.planName = planName;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.startTime = startTime;
        this.endTime = endTime;
        this.updateTime = new Date();
    }
}
