package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {



   private Long userId;
    private String username;


    private String token;




    private Date createTime;


    private Date updateTime;
}
