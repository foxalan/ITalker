package com.alan.push.common;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function :
 *         Issue :
 */

public class Common {

    /**
     * 一些不可变的永恒的参数
     * 通常用于一些配置
     */
    public interface Constance {

        String REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$";
        String API_URL = "http://192.168.232.161:8080/api/";

    }

}
