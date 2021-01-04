package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.vo.FundBaseVO;
import cn.coolwang.crawler.fund.vo.FundRealtimeInfoVO;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

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

    @SneakyThrows
    @Test
    public void getAllFund() {
        FundCrawler fundCrawler = new FundCrawler();
        List<FundBaseVO> fundBaseVOS = fundCrawler.getAllFund();
        //打印到控制台
//        System.out.printf("%6s%20s%10s%40s%40s","基金代码","基金名称","基金类型","基金名称拼音简称","基金名称拼音");
//        System.out.println();
//        fundBaseVOS.forEach(fund -> {
//            System.out.printf("%6s%20s%10s%40s%40s",fund.getFundCode(),fund.getFundName(),fund.getFundType(),fund.getPinyinJc(),fund.getPinyinQc());
//            System.out.println();
//        });

        //输出到CSV
        File file = new File("./allFund.csv");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("基金代码", "基金名称", "基金类型", "基金名称拼音简称", "基金名称拼音");
        CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
        for (FundBaseVO fund : fundBaseVOS) {
            //基金代码增加 ="" ，避免excel打开csv时因纯数字省略掉前面的0
            csvPrinter.printRecord("=\"" + fund.getFundCode() + "\"", fund.getFundName(), fund.getFundType(), fund.getPinyinJc(), fund.getPinyinQc());
        }
        csvPrinter.flush();
        csvPrinter.close();
        System.out.println("获取全部基金信息完成");
    }

}