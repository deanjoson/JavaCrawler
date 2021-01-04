package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.util.UserAgentUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基金爬虫
 *
 * @author 邓军
 * @date 2021-01-04
 * @version 1.0
 */
public class FundCrawler {

    /**
     * 实时信息
     */
    static String realtime_info_url = "http://fundgz.1234567.com.cn/js/{0}.js";

    @SneakyThrows
    public FundRealtimeInfoVO getFundRealtimeInfo(String fundCode){
        Connection.Response res = Jsoup.connect("http://fundgz.1234567.com.cn/js/" + fundCode + ".js")
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
        }else {
            return null;
        }
    }


}
