package cn.coolwang.crawler.fund.service;

import cn.coolwang.crawler.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashSet;
import java.util.Set;

public class ChenxinCrawler {

    private WebDriver webDriver;

    private Set<Cookie> cookies;


    public void login() {
        init();
        String url = "https://www.morningstar.cn/membership/signin.aspx?ReturnUrl=/main/default.aspx";
        //
        webDriver.get(url);
        // //账号
        // webDriver.findElement(By.id("emailTxt")).sendKeys("dengjun@coolwang.com");
        // //密码
        // webDriver.findElement(By.id("pwdValue")).sendKeys("Dj5201314");
        // //验证码
        // Scanner sc = new Scanner(System.in);
        // System.out.println("请输入验证码:");
        // //读取字符串型输入
        // webDriver.findElement(By.id("txtCheckCode")).sendKeys(sc.nextLine());
        // //获取点击按钮,模拟点击
        // webDriver.findElement(By.id("loginGo")).click();
        //
        // //很重要的一步获取登陆后的cookies
        // cookies = webDriver.manage().getCookies();
        // for (Cookie cookie : cookies) {
        //     System.out.println(cookie);
        // }
        cookies = new HashSet<>();
        cookies.add(new Cookie("authWeb","49FE15CFA43D110BC9B55479B73C1D3DC487C452462394544E5800480CFAA9D898CE551D81E33D4A374BD56CC97695295694E9022505F954D72E7F2A559EC910DAEC78E5B5FDA2145B3CF4FF7BE16923BC12CC46547DC9C993DE72E1232BA9CFF14F974B8E74F6ED871E255A2D2F4E03FCA57DDF","www.morningstar.cn","/",null));
        // cookies.add(new Cookie("MS_LocalEmailAddr","dengjun@coolwang.com=","www.morningstar.cn","/",null));
        // cookies.add(new Cookie("user","username=dengjun@coolwang.com&nickname=coolwang88&status=Free&password=q3doVedniXqpjc/hQWDBvA==","www.morningstar.cn","/",null));
        // cookies.add(new Cookie("MSCC","vEAFTUzuL9g=","www.morningstar.cn","/",null));
        // cookies.add(new Cookie("ASP.NET_SessionId","ckw0eg554amg3vm2okqu4f55","www.morningstar.cn","/",null));
    }

    public String getChenXinCode(String fundCode){
        String url = "https://www.morningstar.cn/handler/fundsearch.ashx?q=" + fundCode +"&limit=31&timestamp=" + String.valueOf(System.currentTimeMillis()).substring(0,13);
        if (CollectionUtils.isEmpty(cookies)){
            login();
        }
        for (Cookie cookie : cookies) {
            webDriver.manage().addCookie(cookie);
        }

        webDriver.get(url);
        String resource = webDriver.getPageSource();
        Document document = Jsoup.parse(resource);
        String jsonStr = document.select("pre").text();
        return ((JSONObject)JSON.parseArray(jsonStr).get(0)).getString("FundClassId");
    }

    public void getChenxinFund(String chenxinCode){
        String url ="https://www.morningstar.cn/quicktake/" + chenxinCode +"?place=qq";
        webDriver.get(url);
        String resource = webDriver.getPageSource();
        Document document = Jsoup.parse(resource);
        System.out.println(document);
        //晨星分类
        String category = document.select("span[class=category]").text();
        //成立日期
        String inception = document.select("span[class=inception]").text();
        //总净资产（亿元）
        String asset = document.select("span[class=asset]").text();
        //风险评估
        Elements qtRiskLi = document.select("#qt_risk > li");
        //平均回报三年
        String pjhb_3n = qtRiskLi.get(8).text();
        //平均回报三年评价
        String pjhb_3npj = qtRiskLi.get(9).text();
        //平均回报五年
        String pjhb_5n = qtRiskLi.get(10).text();
        //平均回报五年评价
        String pjhb_5npj = qtRiskLi.get(11).text();
        //平均回报十年
        String pjhb_10n = qtRiskLi.get(12).text();
        //平均回报十年评价
        String pjhb_10npj = qtRiskLi.get(13).text();
        //标准差三年
        String bjc_3n = qtRiskLi.get(15).text();
        //标准差三年评价
        String bjc_3npj = qtRiskLi.get(16).text();
        
        
        

    }

    private synchronized void init() {
        if (webDriver == null) {
            synchronized (this) {
                System.getProperties().setProperty("webdriver.chrome.driver", "/Users/dengjun/data.localized/dengjun.localized/workspace/java/JavaCrawler/src/main/resources/selenium/chromedriver");
                ChromeOptions options = new ChromeOptions();
                // 禁用沙盒
                options.addArguments("no-sandbox");
                // 禁止加载图片
                // options.addArguments("blink-settings=imagesEnabled=false");
                // options.addArguments("--whitelisted-ips=\"\"");
                // 无界面模式 在Linux中一定是不能唤起浏览器的（很重要）
                options.setHeadless(Boolean.TRUE);
                webDriver = new ChromeDriver(options);
            }
        }
    }

    public static void main(String[] args) {
        ChenxinCrawler crawler = new ChenxinCrawler();

        String chenxinCode = crawler.getChenXinCode("320007");
        crawler.getChenxinFund(chenxinCode);
        System.out.println();
    }
}
