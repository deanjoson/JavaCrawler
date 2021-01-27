package cn.coolwang.crawler.fund.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumCrawler {

    public void  fundSyl(String fundCode){
        // System.getProperties().setProperty("webdriver.chrome.driver","D:/chromedriver_win32/chromedriver.exe");
        //开启webDriver进程
    }




    public static void main(String[] args) {
        System.getProperties().setProperty("webdriver.chrome.driver","/Users/dengjun/data.localized/dengjun.localized/workspace/java/JavaCrawler/src/main/resources/selenium/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // 禁用沙盒
        options.addArguments("no-sandbox");
        // 禁止加载图片
        options.addArguments("blink-settings=imagesEnabled=false");
        options.addArguments("--whitelisted-ips=\"\"");
        // 无界面模式 在Linux中一定是不能唤起浏览器的（很重要）
        options.setHeadless(Boolean.TRUE);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("http://fundf10.eastmoney.com/jdzf_005827.html");
        String html = webDriver.getPageSource();
        Document document = Jsoup.parse(html);

        Elements p = document.select("div[class=bs_gl] > p");

        // //成立日期
        System.out.println("成立日期:  " + p.select("span").first().text());
        System.out.println("基金经理:  " + p.select("a").first().text());
        System.out.println("类型:  " + p.select("span").get(1).text());
        System.out.println("管理人:  " + p.select("a").get(1).text());
        System.out.println("资产规模:  " + p.select("span").get(2).text().split(" ")[0]);
        System.out.println("资产规模统计时间:  " + p.select("span").get(2).text().split(" ")[1]);
        // System.out.println(Jsoup.select(document,"//div[@class='bs_gl']/p/label").list());
        // System.out.println(html);
        webDriver.get("http://work.chanxins.com");
        System.out.println(webDriver.getTitle());
        webDriver.close();
        webDriver.quit();
    }
}
