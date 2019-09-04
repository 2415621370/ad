package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 推广单元-地域限制
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 14:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_district")
public class AdUnitDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 单元id
     */
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    /**
     * 省
     */
    @Column(name = "province", nullable = false)
    private String province;

    /**
     * 市
     */
    @Column(name = "city", nullable = false)
    private String city;

    public AdUnitDistrict( Long unitId,String province,String city){

        this.unitId = unitId;
        this.province = province;
        this.city = city;

    }
}
