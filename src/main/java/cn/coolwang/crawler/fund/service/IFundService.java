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
     * 保存所有基金基础信息
     */
    void saveAllFundBase();
    /**
     * 更新基金详细信息
     *
     * @param fundCode
     */
    void updateFundDetail(String fundCode);

    /**
     * 查询基金详细信息
     *
     * @param fundCode 基金代码
     * @return 基金实体对象
     */
    FundEntity queryFundDetail(String fundCode);

}
