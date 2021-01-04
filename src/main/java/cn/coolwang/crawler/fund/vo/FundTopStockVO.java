package cn.coolwang.crawler.fund.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 基金股票持仓信息
 *
 * @author 邓军
 * @date
 * @version 1.0
 */
@Data
public class FundTopStockVO {

    /**
     * 股票代码
     */
    private String fundCode;

    /**
     * 股票名称
     */
    private String fundName;

    /**
     * 年度
     */
    private String year;

    /**
     * 季度
     */
    private String quarter;

    /**
     * 截止时间
     */
    private String endTime;

    /**
     * 持仓股票信息
     */
    private List<Stock> stocks;

    /**
     * 股票信息
     *
     * @author 邓军
     * @date
     * @version 1.0
     */
    @Data
    @Builder
    public static class Stock{

        /**
         * 序号
         * 加*号代表进入上市公司的十大流通股东却没有进入单只基金前十大重仓股的个股
         */
        private String sn;

        /**
         * 股票代码
         */
        private String stockCode;

        /**
         * 股票名称
         */
        private String stockName;

        /**
         * 占净值比例
         */
        private String stockProportion;

        /**
         * 持股数（万股）
         */
        private String stockAmount;

        /**
         * 持仓市值（万元）
         */
        private String stockValue;
    }

}
