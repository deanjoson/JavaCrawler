package cn.coolwang.crawler.util;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }


    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
