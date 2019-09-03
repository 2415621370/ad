package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/3 15:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeywordRequest {

  List<UnitKeyword> unitKeyWords;


    public boolean validate(){
        return !CollectionUtils.isEmpty(unitKeyWords);
    }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UnitKeyword{
      private Long unitId;
      private String keyWord;
  }
}
