package com.itcanteen.sponsor.constant;

/**
 * 常量类
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 11:55
 */
public class Constants {


    public static final String JWT_SIGN_STR="itcanteen";

    /**
     * 错误信息类
     */
    public static class ErrorMsg{
        public static final String REQUEST_PARAMS_ERROR="请求参数错误";
        public static final String SAME_USER_ERROR="已存在相同的用户";
        public static final String CAN_NOT_FIND_USER="没有找到该用户";
        public static final String SAME_PLAN_NAME="已存在相同的广告计划";
        public static final String CAN_NOT_FIND_PLAN="没有找到该广告计划";
        public static final String SAME_UNIT_ERROR="已存在相同的推广单元";
    }



}
