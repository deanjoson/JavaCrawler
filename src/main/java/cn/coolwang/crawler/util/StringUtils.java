package cn.coolwang.crawler.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
public class StringUtils {

    /**
     * 判断传入字符串是否为空字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断传入字符串是否为非空字符串
     *
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 占位符替换
     * eg： originStr -> 这是需要{0}的数据
     * replaceStrArray -> ["替换"]
     * return -> 这是需要替换的数据
     *
     * @param originStr       原始字符串
     * @param replaceStrArray 占位符替换数据数组
     * @return 替换后的字符串
     */
    public static String placeholder(String originStr, String... replaceStrArray) {
        final String placeholder = "\\{(\\d)}";
        if (isEmpty(originStr)) {
            return originStr;
        }
        Matcher m = Pattern.compile(placeholder).matcher(originStr);
        while (m.find()) {
            try {
                originStr = originStr.replace(m.group(), replaceStrArray[Integer.parseInt(m.group(1))]);
            } catch (Exception ignored) {
            }
        }
        return originStr;
    }

    /**
     * 占位符替换
     * eg： originStr -> 这是需要{0}的数据
     * replaceStrArray -> ["替换"]
     * return -> 这是需要替换的数据
     *
     * @param originStr       原始字符串
     * @param replaceStrArray 占位符替换数据数组
     * @return 替换后的字符串
     */
    public static String placeholder(String originStr, List<String> replaceStrArray) {
        if (CollectionUtils.isEmpty(replaceStrArray)) {
            return originStr;
        }
        final String placeholder = "\\{(\\d)}";
        if (isEmpty(originStr)) {
            return originStr;
        }
        Matcher m = Pattern.compile(placeholder).matcher(originStr);
        while (m.find()) {
            try {
                originStr = originStr.replace(m.group(), replaceStrArray.get(Integer.parseInt(m.group(1))));
            } catch (Exception ignored) {
            }
        }
        return originStr;
    }

}
