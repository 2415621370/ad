package com.itcanteen.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/5 14:22
 */
public class ParseJwtTest {

    public static void main(String[] args){
        Claims claims = Jwts.parser().setSigningKey("itcanteen")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiIxNzAyQiIsImlhdCI6MTU2NzY3MzUxNywiZXhwIjoxNTY3Njc0MjQwfQ.QENf38WWDhcq9FOH5q2q0s3Z61C74lb6vY9m1Vqvqw8000")
                .getBody();


        System.out.println(claims.getId());
        System.out.println(claims.getSubject());


    }



}
