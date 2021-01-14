package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.service.IFundService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class FundServiceTest {

    @Autowired
    private IFundService fundService;

    @Test
    public void queryFundDetail() {
        List<String> fundCodes = Arrays.asList("260108", "005827", "162412", "000596", "002692", "005962", "002001", "000727", "004075", "003884", "163406", "161005");
        for (String fundCode : fundCodes) {
            log.info("基金详情： {}", fundService.queryFundDetail(fundCode));
        }
    }
}
