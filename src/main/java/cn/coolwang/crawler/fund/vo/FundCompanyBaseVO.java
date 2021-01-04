package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 基金公司基础信息
 *
 * @author 邓军
 * @date 2021-01-04
 * @version 1.0
 */
@Data
@Builder
public class FundCompanyBaseVO {

    /**
     * 基金公司代码
     */
    private String companyCode;

    /**
     * 基金公司名称
     */
    private String companyName;
}
