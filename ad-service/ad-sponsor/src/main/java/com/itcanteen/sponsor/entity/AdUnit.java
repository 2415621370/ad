package com.itcanteen.sponsor.entity;

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
}
