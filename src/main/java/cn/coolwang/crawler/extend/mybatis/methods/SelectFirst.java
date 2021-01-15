package cn.coolwang.crawler.extend.mybatis.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件一条数据，忽略逻辑删除数据
 * 参考 {@link com.baomidou.mybatisplus.core.injector.methods.SelectOne}
 *
 * @author 邓军
 * @version 1.0
 * @date 2020年07月16日14:05:25
 */
public class SelectFirst extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>%s SELECT %s FROM %s %s %s limit 1\n</script>";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(sql,
            sqlFirst(), sqlSelectColumns(tableInfo, true), tableInfo.getTableName(),
            sqlWhereEntityWrapper(true, tableInfo), sqlComment()), modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, "selectFirst", sqlSource, tableInfo);
    }
}
