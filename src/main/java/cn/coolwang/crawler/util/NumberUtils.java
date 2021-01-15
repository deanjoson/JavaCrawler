package cn.coolwang.crawler.util;

import java.util.Objects;

/**
 * 数字转换
 *
 * @author 邓军
 * @version 1.0
 * @date
 */
public class NumberUtils {

    public static Double parseDouble(String val) {
        if (StringUtils.isEmpty(val)) {
            return null;
        }
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double parseDouble(String val, Double defaultVal) {
        if (StringUtils.isEmpty(val)) {
            return defaultVal;
        }
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Double parseDouble(String val, String replaceNull) {
        return parseDouble(val,replaceNull,null);
    }

    public static Double parseDouble(String val, String replaceNull,Double defaultVal) {
        if (StringUtils.isEmpty(val)) {
            return defaultVal;
        }
        try {
            return Double.parseDouble(val.replaceAll(replaceNull,""));
        } catch (Exception e) {
            return defaultVal;
        }
    }
}
