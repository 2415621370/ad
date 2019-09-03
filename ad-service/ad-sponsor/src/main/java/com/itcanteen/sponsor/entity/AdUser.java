package com.itcanteen.sponsor.entity;

import com.itcanteen.sponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * 用户表：广告主
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:16
 */

@Entity
@Table(name="ad_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUser {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",nullable = false)
    private Long  id;


    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "user_status",nullable = false)
    private  Integer userSatus;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;


    public AdUser(String username,String token){
        this.username = username;
        this.token = token;
        this.userSatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
