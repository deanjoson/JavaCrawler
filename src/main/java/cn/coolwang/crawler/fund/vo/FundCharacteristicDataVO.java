package cn.coolwang.crawler.fund.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
     * 特色数据：近一年夏普比率  夏普比率：表示每承受一单位风险，预期可以拿到多少超额收益。指标越大越好。夏普比率越大则基金性价比越高
     */
    private Double sharp1n;

    /**
     * 特色数据：近一年波动率 波动率：表示收益率变化程度的指标，更直观地表述就是风险。指标越小越好。波动率越大则风险也越高
     */
    private Double stddev1n;

    /**
     * 特色数据：近一年最大回撤 最大回撤：表示基金净值从高到低到下降幅度。指标越小越好。最大回撤越大则风险控制能力越差。
     */
    private Double maxretra1n;

    /**
     * 特色数据：近一月定投人数
     */
    private Integer dtcount1y;

    /**
     * 特色数据：近一月访问量
     */
    private Integer pv1y;

    /**
     * 特色数据：用户平均持有天数
     */
    private Double avghold;
}
