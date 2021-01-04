package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 基金实时信息VO
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
@Data
@Builder
public class FundRealtimeInfoVO {

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 净值日期
     */
    private String jzrq;

    /**
     * 单位净值
     */
    private String dwjz;

    /**
     * 净值估算
     */
    private String gsz;

    /**
     * 估算收益率
     */
    private String gszzl;
    /**
     * 估算时间
     */
    private String gztime;
}
