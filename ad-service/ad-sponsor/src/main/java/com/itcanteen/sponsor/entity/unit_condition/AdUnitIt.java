package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 14:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_it")
public class AdUnitIt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "unit_id", nullable = false)
    private Long unitId;


    /**
     * 兴趣标签
     */
    @Column(name = "it_tag", nullable = false)
    private String itTag;

    public AdUnitIt(Long unitId,String itTag){
        this.unitId = unitId;
        this.itTag = itTag;
    }
}
