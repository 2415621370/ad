package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 14:27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creative_unit")
public class CreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "creative_id", nullable = false)
    private Long creativeId;


    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    public CreativeUnit(Long creativeId,Long unitId){
        this.creativeId=creativeId;
        this.unitId = unitId;
    }
}
