package com.itcanteen.mysql.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 17:29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinLogKafkaData {

    private String tableName;

    private String eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
