package cn.coolwang.crawler.pojo;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.Map;

/**
 * 基础Bean对象模型，提供对象转换及toString方法
 *
 * @author 邓军
 * @version 1.0
 * @date 2020年07月13日10:51:59
 */
public class BaseModel implements Serializable {

    /**
     * 将当前对象转换为Map
     *
     * @return
     */
    public Map<String, Object> covertMap() {
        return covertMap(false, false);
    }

    /**
     * 将当前对象转换为Map
     *
     * @param underLineKey 是否将KEY转换为小写
     * @return
     */
    public Map<String, Object> covertMap(boolean underLineKey) {
        return covertMap(underLineKey, false);
    }

    /**
     * 将当前对象转换为Map
     *
     * @param underLineKey    是否将KEY转换为小写
     * @param ignoreNullValue 是否忽略空值字段
     * @return
     */
    public Map<String, Object> covertMap(boolean underLineKey, boolean ignoreNullValue) {
        return BeanUtil.beanToMap(this, underLineKey, ignoreNullValue);
    }

    /**
     * 将当前对象转换为另一对象
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T covertBean(Class<T> tClass) {
        return BeanUtil.toBean(this, tClass);
    }

    @SneakyThrows
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
