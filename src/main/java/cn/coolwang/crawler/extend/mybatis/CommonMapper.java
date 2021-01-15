package cn.coolwang.crawler.extend.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据访问通用mapper
 *
 * @author 邓军
 * @version 1.0
 * @date 2020年07月16日15:12:50
 */
public interface CommonMapper<T> extends BaseMapper<T> {


    /**
     * 替换一条记录
     *
     * @param entity 实体对象
     */
    int replaceInto(T entity);

    /**
     * 如果要自动填充，@{@code Param}(xx) xx参数名必须是 list/collection/array 3个的其中之一
     *
     * @param batchList
     * @return
     */
    int insertAll(@Param("list") List<T> batchList);


    /**
     * 根据 entity 条件，查询第一条记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    T selectFirst(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

}
