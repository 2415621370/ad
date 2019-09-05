package com.itcanteen.test;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/5 14:15
 */
public class CreateJwt {
    public static void main(String[] args){
        JwtBuilder jwtBuilder = Jwts.builder().setId("123")
                .setSubject("1702B")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "itcanteen")
                .setExpiration(new Date(new Date().getTime()+60000*12));


        System.out.print(jwtBuilder.compact());


    }
}
