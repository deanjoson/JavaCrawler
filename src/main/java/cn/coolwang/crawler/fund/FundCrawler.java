package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.vo.FundBaseVO;
import cn.coolwang.crawler.fund.vo.FundRealtimeInfoVO;
import cn.coolwang.crawler.util.JavaScriptUtils;
import cn.coolwang.crawler.util.StringUtils;
import cn.coolwang.crawler.util.UserAgentUtils;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

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
     * 天天基金：实时信息
     */
    static String realtime_info_url = "http://fundgz.1234567.com.cn/js/{0}.js";

    /**
     * 天天基金：所有基金
     */
    static String all_fund_url = "http://fund.eastmoney.com/js/fundcode_search.js";

    /**
     * 获取基金实时信息
     *
     * @param fundCode
     * @return
     */
    @SneakyThrows
    public FundRealtimeInfoVO getFundRealtimeInfo(String fundCode) {
        Connection.Response res = Jsoup.connect(StringUtils.placeholder(realtime_info_url, fundCode))
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
        Connection.Response res = Jsoup.connect(all_fund_url)
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


}
