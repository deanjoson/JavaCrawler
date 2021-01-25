package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 基金特殊数据
 *
 * @author 邓军
 * @version 1.0
 * @date
 */
@Data
@Builder
public class FundCharacteristicDataVO {

    /**
     * 近一年夏普比率  夏普比率：表示每承受一单位风险，预期可以拿到多少超额收益。指标越大越好。夏普比率越大则基金性价比越高
     */
    private double sharp1n;

    /**
     * 近一年波动率 波动率：表示收益率变化程度的指标，更直观地表述就是风险。指标越小越好。波动率越大则风险也越高
     */
    private double stddev1n;
    /**
     * 近一年最大回撤 最大回撤：表示基金净值从高到低到下降幅度。指标越小越好。最大回撤越大则风险控制能力越差。
     */
    private double maxretra1n;
    /**
     * 近一月定投人数
     */
    private int dtcount1y;
    /**
     * 近一月访问量
     */
    private int pv1y;
    /**
     * 用户平均持有天数
     */
    private double avghold;
}
