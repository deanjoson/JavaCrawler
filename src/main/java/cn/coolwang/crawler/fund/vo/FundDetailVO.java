package cn.coolwang.crawler.fund.vo;

import cn.coolwang.crawler.pojo.BaseModel;
import lombok.Builder;
import lombok.Data;

/**
 * 基金详细信息
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-14
 */
@Data
@Builder
public class FundDetailVO extends BaseModel {

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 原始费率
     */
    private Double originalRate;

    /**
     * 购买费率
     */
    private Double purchaseRate;

    /**
     * 最小申购金额
     */
    private Integer minSubAmount;

    /**
     * 近一月收益率
     */
    private Double syl1y;

    /**
     * 近三月收益率
     */
    private Double syl3y;

    /**
     * 近6月收益率
     */
    private Double syl6y;

    /**
     * 近一年收益率
     */
    private Double syl1n;

    /**
     * 现任基金经理代码
     */
    private String managerCode;

    /**
     * 现任基金经理
     */
    private String managerName;
}
