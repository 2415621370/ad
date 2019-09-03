package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 关键词的限制
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 14:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit_keyword")
public class AdUnitKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "unit_id", nullable = false)
    private Long unitId;


    /**
     * 关键词
     */
    @Column(name = "keyword", nullable = false)
    private String keyword;

    public AdUnitKeyword( Long unitId,String keyword){
        this.unitId = unitId;
        this.keyword = keyword;
    }
}
