package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 基金基础信息
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
@Data
@Builder
public class FundBaseVO {

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 基金拼音简称
     */
    private String pinyinJc;

    /**
     * 基金类型
     */
    private String fundType;

    /**
     * 基金名称拼音全称
     */
    private String pinyinQc;

}
