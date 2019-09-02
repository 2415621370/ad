package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:40
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;


    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
