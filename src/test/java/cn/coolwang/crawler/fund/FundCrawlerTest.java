package cn.coolwang.crawler.fund;

import org.junit.jupiter.api.Test;

/**
 * 基金爬虫测试
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
public class FundCrawlerTest {

    @Test
    public void getFundRealtimeInfo() {
        FundCrawler fundCrawler = new FundCrawler();
        FundRealtimeInfoVO realtimeInfo = fundCrawler.getFundRealtimeInfo("161725");
        System.out.println("基金代码：" + realtimeInfo.getFundCode());
        System.out.println("基金名称：" + realtimeInfo.getName());
        System.out.println("净值日期：" + realtimeInfo.getJzrq());
        System.out.println("单位净值：" + realtimeInfo.getDwjz());
        System.out.println("净值估算：" + realtimeInfo.getGsz());
        System.out.println("估算收益率：" + realtimeInfo.getGszzl() + "%");
        System.out.println("估算时间：" + realtimeInfo.getGztime());
    }

}