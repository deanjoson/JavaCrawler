package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 基金公司
 *
 * @author 邓军
 * @version 1.0
 * @date
 */
@Data
@Builder
public class FundCompanyVO {

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司名称(全称)
     */
    private String companyFullName;

    /**
     * 地址
     */
    private String position;

    /**
     * 总经理
     */
    private String generalManager;

    /**
     * 网站地址
     */
    private String websiteUrl;

    /**
     * 客服热线
     */
    private String telephone;
    /**
     * 管理规模
     */
    private Double manageScale;
    /**
     * 基金数量
     */
    private Integer fundCount;
    /**
     * 经理数量
     */
    private Integer managerCount;

    /**
     * 成立日期
     */

    private String publishDate;

    /**
     * 公司性质
     */

    private String companyProperty;
}
