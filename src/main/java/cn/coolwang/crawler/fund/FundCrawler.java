package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.vo.FundBaseVO;
import cn.coolwang.crawler.fund.vo.FundRealtimeInfoVO;
import cn.coolwang.crawler.fund.vo.FundTopStockVO;
import cn.coolwang.crawler.util.JavaScriptUtils;
import cn.coolwang.crawler.util.StringUtils;
import cn.coolwang.crawler.util.UserAgentUtils;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基金爬虫
 *
 * @author 邓军
 * @version 1.0
 * @date 2021-01-04
 */
public class FundCrawler {


    /**
     * 获取基金实时信息
     *
     * @param fundCode
     * @return
     */
    @SneakyThrows
    public FundRealtimeInfoVO getFundRealtimeInfo(String fundCode) {
        String realtimeInfoUrl = "http://fundgz.1234567.com.cn/js/{0}.js";
        String url = StringUtils.placeholder(realtimeInfoUrl, fundCode);
        Connection.Response res = Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "text/*")
                .userAgent(UserAgentUtils.randomUserAgent())
                .timeout(10000).ignoreContentType(true).execute();
        String body = res.body();

        String regex = "^jsonpgz\\((.*?)\\);$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        if (matcher.find()) {
            JSONObject json = JSONObject.parseObject(matcher.group(1));
            return FundRealtimeInfoVO.builder()
                    .fundCode(json.getString("fundcode"))
                    .name(json.getString("name"))
                    .jzrq(json.getString("jzrq"))
                    .dwjz(json.getString("dwjz"))
                    .gsz(json.getString("gsz"))
                    .gszzl(json.getString("gszzl"))
                    .gztime(json.getString("gztime"))
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 获取天天基金所有基金
     *
     * @return 基金基本信息列表
     */
    @SneakyThrows
    public List<FundBaseVO> getAllFund() {
        String url = "http://fund.eastmoney.com/js/fundcode_search.js";
        Connection.Response res = Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "text/*")
                .userAgent(UserAgentUtils.randomUserAgent())
                .timeout(10000).ignoreContentType(true).execute();
        String body = res.body();
        ScriptObjectMirror obj = (ScriptObjectMirror) JavaScriptUtils.executeForAttribute(body, "r");
        List<FundBaseVO> fundBaseVOS = new ArrayList<>(obj.size());
        for (int i = 0; i < obj.size(); i++) {
            ScriptObjectMirror sub = (ScriptObjectMirror) obj.get(String.valueOf(i));
            //sub 0: 基金代码
            //sub 1: 基金名称拼音简称
            //sub 2: 基金名称
            //sub 3: 基金类型
            //sub 4: 基金名称拼音全拼
            FundBaseVO fundBaseVO = FundBaseVO.builder()
                    .fundCode(sub.get("0").toString())
                    .pinyinJc(sub.get("1").toString())
                    .fundName(sub.get("2").toString())
                    .fundType(sub.get("3").toString())
                    .pinyinQc(sub.get("4").toString())
                    .build();
            fundBaseVOS.add(fundBaseVO);
        }
        return fundBaseVOS;
    }

    /**
     * 获取基金持仓股票排名
     * @param fundCode 基金代码
     * @param year 查询年度
     * @param top 排名数量
     */
    @SneakyThrows
    public List<FundTopStockVO> getFundTopStock(String fundCode, int year, int top){
        String topStockUrl = "http://fundf10.eastmoney.com/FundArchivesDatas.aspx?type=jjcc&code=" + fundCode +"&topline=" + top +"&year=" + year;
        String url = StringUtils.placeholder(topStockUrl,fundCode,String.valueOf(top),String.valueOf(year));
        System.out.println("访问地址：" + url);
        Document document = Jsoup.connect(url).get();
        Elements div = document.getElementsByClass("boxitem w790");
        List<FundTopStockVO> fundTopStockVOS = new ArrayList<>(div.size());
        for (Element value : div) {
            FundTopStockVO fundTopStockVO = new FundTopStockVO();
            fundTopStockVO.setFundCode(fundCode);
            String fundName = value.select("h4 > label > a").first().text().trim();
            fundTopStockVO.setFundName(fundName);
            String fundInfo = value.select("h4 > label").first().text().trim();
            fundTopStockVO.setYear(String.valueOf(year));
            //获取季度
            String quarterRegex = "([1-4])季度";
            Pattern quarterPattern = Pattern.compile(quarterRegex);
            Matcher quarterMatcher = quarterPattern.matcher(fundInfo);
            if (quarterMatcher.find()) {
                fundTopStockVO.setQuarter(quarterMatcher.group(0));
            }
            //获取截止时间
            fundTopStockVO.setEndTime(value.select("font[class=px12]").first().text().trim());

            Elements tr = value.select("tbody > tr");
            List<FundTopStockVO.Stock> stocks = new ArrayList<>(tr.size());
            for (Element element : tr) {
                Elements td = element.select("td");
                //序号
                String sn = td.get(0).text();
                //股票代码
                String stockCode = td.get(1).text();
                //股票名称
                String stockName = td.get(2).text();
                //最新价(可能无)
                String price = td.get(3).text();
                //涨跌幅（可能无）
                String zdf = td.get(4).text();
                //相关资讯
                String xgzx = td.get(td.size() - 4).text();
                //占净值比例
                String stockProportion = td.get(td.size() - 3).text();
                //持股数（万股）
                String stockAmount = td.get(td.size() - 2).text();
                //持仓市值（万元）
                String stockValue = td.get(td.size() - 1).text();
                FundTopStockVO.Stock stock = FundTopStockVO.Stock.builder()
                        .sn(sn)
                        .stockCode(stockCode)
                        .stockName(stockName)
                        .stockProportion(stockProportion)
                        .stockAmount(stockAmount)
                        .stockValue(stockValue)
                        .build();
                stocks.add(stock);
            }
            fundTopStockVO.setStocks(stocks);
            fundTopStockVOS.add(fundTopStockVO);
        }
        return fundTopStockVOS;
    }


}
