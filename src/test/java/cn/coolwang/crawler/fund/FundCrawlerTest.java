package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.service.FundCrawler;
import cn.coolwang.crawler.fund.vo.*;
import cn.coolwang.crawler.util.StringUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基金爬虫测试
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
@SpringBootTest
@Slf4j
public class FundCrawlerTest {

    @Autowired
    private FundCrawler fundCrawler;


    @Test
    public void getFundRealtimeInfo() {
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

    @SneakyThrows
    @Test
    public void getFundDetail() {
        log.info("基金详情： {}", fundCrawler.getFundDetail("005827"));
    }

    @SneakyThrows
    @Test
    public void getFundDetail2() {
        fundCrawler.getFundDetail2("005827");
    }

    @SneakyThrows
    @Test
    public void getFundJdSyl() {
       fundCrawler.getFundJdSyl("161725");
    }

    @Test
    public void getFundTopStock() {
        List<FundTopStockVO> fundTopStockVOS = fundCrawler.getFundTopStock("161725", 2020, 10);
        for (FundTopStockVO fundTopStockVO : fundTopStockVOS) {
            String print = "{0} {1}年{2}持仓股票排名（截止时间：{3}）";
            System.out.println(StringUtils.placeholder(print, fundTopStockVO.getFundName(), fundTopStockVO.getYear(), fundTopStockVO.getQuarter(), fundTopStockVO.getEndTime()));
            System.out.printf("%8s", "序号");
            System.out.printf("%10s", "股票代码");
            System.out.printf("%20s", "股票名称");
            System.out.printf("%18s", "占净值比例");
            System.out.printf("%16s", "持仓股数，万股");
            System.out.printf("%16s", "持仓市值，万元");
            System.out.println();
            for (FundTopStockVO.Stock stock : fundTopStockVO.getStocks()) {
                System.out.printf("%8s", stock.getSn());
                System.out.printf("%13s", stock.getStockCode());
                System.out.printf("%20s", stock.getStockName());
                System.out.printf("%20s", stock.getStockProportion());
                System.out.printf("%20s", stock.getStockAmount());
                System.out.printf("%20s", stock.getStockValue());
                System.out.println();
            }
        }
    }

    @Test
    void getAllFundComp() {
        List<FundCompanyBaseVO> companyList = fundCrawler.getAllFundCompany();
        System.out.printf("%8s", "基金公司代码");
        System.out.printf("%18s", "基金公司名称");
        System.out.println();
        for (FundCompanyBaseVO fundCompanyBaseVO : companyList) {
            System.out.printf("%10s", fundCompanyBaseVO.getCompanyCode());
            System.out.printf("%20s", fundCompanyBaseVO.getCompanyName());
            System.out.println();
        }
    }

    @Test
    void getFundCompanyInfo() {
        FundCompanyVO fundCompany = fundCrawler.getFundCompanyInfo("80000248", "广发基金");
        System.out.println("基金代码    : " + fundCompany.getCompanyCode());
        System.out.println("基金公司    : " + fundCompany.getCompanyName());
        System.out.println("基金公司全称 : " + fundCompany.getCompanyFullName());
        System.out.println("办公地址    : " + fundCompany.getPosition());
        System.out.println("总经理      : " + fundCompany.getGeneralManager());
        System.out.println("网站地址    : " + fundCompany.getWebsiteUrl());
        System.out.println("客服热线    : " + fundCompany.getTelephone());
        System.out.println("管理规模    : " + fundCompany.getManageScale());
        System.out.println("基金数量    : " + fundCompany.getFundCount());
        System.out.println("经理数量    : " + fundCompany.getManagerCount());
        System.out.println("成立日期    : " + fundCompany.getPublishDate());
        System.out.println("公司性质    : " + fundCompany.getCompanyProperty());
    }

    /**
     * 导出所有公司到CSV （管理规模排序）
     */
    @SneakyThrows
    @Test
    void exportAllFundCompanyCSV() {
        //获取所有公司列表
        List<FundCompanyBaseVO> companyList = fundCrawler.getAllFundCompany();
        //循环获取公司详细信息（注意：没有使用代理不可使用并发，否则会因反扒机制而失败）
        List<FundCompanyVO> newCompanyList = companyList.stream()
                .map(cp -> fundCrawler.getFundCompanyInfo(cp.getCompanyCode(), cp.getCompanyName()))
                .sorted(Comparator.comparingDouble(FundCompanyVO::getManageScale).reversed())
                .collect(Collectors.toList());
        File file = new File("./fundCompany.csv");
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("公司代码", "公司名称", "公司名称(全称)", "地址", "总经理", "网站地址", "客服热线", "管理规模", "基金数量", "经理数量", "成立日期", "公司性质");
        CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
        for (FundCompanyVO fundCompany : newCompanyList) {
            csvPrinter.printRecord(
                    fundCompany.getCompanyCode(),
                    fundCompany.getCompanyName(),
                    fundCompany.getCompanyFullName(),
                    fundCompany.getPosition(),
                    fundCompany.getGeneralManager(),
                    fundCompany.getWebsiteUrl(),
                    fundCompany.getTelephone(),
                    fundCompany.getManageScale(),
                    fundCompany.getFundCount(),
                    fundCompany.getManagerCount(),
                    fundCompany.getPublishDate(),
                    fundCompany.getCompanyProperty());
        }
        csvPrinter.flush();
        csvPrinter.close();
    }

}