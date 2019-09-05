package com.itcanteen.sponsor.utils;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.constant.Constants;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 14:16
 */
public class CommonUtils {

    private static String[] parsePatterns={
            "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"
    };

    /**
     * 获取字符串MD5值
     * @param str
     * @return
     */
    public static String md5(String str){
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    /**
     *获取日期类型
     * @param dateString
     * @return
     * @throws AdException
     */
    public static Date parseStringToDate(String dateString) throws AdException {

        try {
            return DateUtils.parseDate(dateString,parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AdException(e.getMessage());
        }

    }


    public static String jwtSign(String userId,String username){
        JwtBuilder jwtBuilder = Jwts.builder().setId(userId) //y用户id
                .setSubject(username) //用户名
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_SIGN_STR)
                .setExpiration(new Date(new Date().getTime()+60000*120));

        return jwtBuilder.compact();
    }
}
