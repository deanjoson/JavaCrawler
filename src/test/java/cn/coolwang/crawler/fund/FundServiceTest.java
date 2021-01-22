package cn.coolwang.crawler.fund;

import cn.coolwang.crawler.fund.entity.FundEntity;
import cn.coolwang.crawler.fund.mapper.FundMapper;
import cn.coolwang.crawler.fund.service.IFundService;
import cn.coolwang.crawler.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class FundServiceTest {

    @Autowired
    private IFundService fundService;

    @Autowired
    private FundMapper fundMapper;

    @Test
    public void queryFundDetail() {
        List<String> fundCodes = Arrays.asList("260108", "005827", "162412", "000596", "002692", "005962", "002001", "000727", "004075", "003884", "163406", "161005");
        for (String fundCode : fundCodes) {
            log.info("基金详情： {}", fundService.queryFundDetail(fundCode));
        }
    }

    @Test
    public void updateAllFundDetail() {
        LambdaQueryWrapper<FundEntity> wrapper = new LambdaQueryWrapper<>();
        // wrapper.isNull(FundEntity::getOriginalRate);
        // wrapper.eq(FundEntity::getFundCode,"005827");
        List<FundEntity> fundEntities = fundMapper.selectList(wrapper);
        log.info("待更新基金数量：{}",fundEntities.size());
        List<FundEntity> fail = new ArrayList<>();
        fundEntities.parallelStream().forEach(fund -> {
            try {
                fundService.updateFundDetail(fund.getFundCode());
            } catch (Exception e) {
                log.warn("基金更新失败：{} {} {}", fund.getFundCode(), fund.getFundName(), fund.getFundType());
                fail.add(fund);
            }
        });

        log.error("失败基金数量：{}", fail.size());
        fail.parallelStream().forEach(fund -> {
            try {
                fundService.updateFundDetail(fund.getFundCode());
            } catch (Exception e) {
                log.warn("基金二次更新失败：{} {} {},移除该基金", fund.getFundCode(), fund.getFundName(), fund.getFundType());
               fundMapper.deleteById(fund.getFundCode());
            }
        });
        log.error("二次失败基金数量：{}", fail.size());
    }


    @Test
    public void saveAllFund() {
        fundService.saveAllFundBase();
        log.info("保存完成");
    }
}


