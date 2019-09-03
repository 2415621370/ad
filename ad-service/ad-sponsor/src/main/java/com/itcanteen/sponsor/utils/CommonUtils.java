package com.itcanteen.sponsor.utils;

import com.itcanteen.common.exception.AdException;
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
}
