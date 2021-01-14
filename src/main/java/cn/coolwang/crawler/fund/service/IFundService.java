package cn.coolwang.crawler.fund.service;

import cn.coolwang.crawler.fund.entity.FundEntity;

/**
 * 基金服务
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-14
 */
public interface IFundService {

    /**
     * 查询基金详细信息
     *
     * @param fundCode 基金代码
     * @return 基金实体对象
     */
    FundEntity queryFundDetail(String fundCode);

}
