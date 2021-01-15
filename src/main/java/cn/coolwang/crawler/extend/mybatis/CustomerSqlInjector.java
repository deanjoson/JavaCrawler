package cn.coolwang.crawler.extend.mybatis;

import cn.coolwang.crawler.extend.mybatis.methods.InsertAll;
import cn.coolwang.crawler.extend.mybatis.methods.ReplaceInto;
import cn.coolwang.crawler.extend.mybatis.methods.SelectFirst;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * 注册自定义的方法
 *
 * @author 邓军
 * @version 1.0
 * @date 2020年07月16日14:32:57
 */
public class CustomerSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectFirst());
        methodList.add(new InsertAll());
        methodList.add(new ReplaceInto());
        return methodList;
    }
}
