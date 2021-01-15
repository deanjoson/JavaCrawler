package cn.coolwang.crawler.fund.service.impl;

import cn.coolwang.crawler.fund.entity.FundEntity;
import cn.coolwang.crawler.fund.entity.FundJdzfEntity;
import cn.coolwang.crawler.fund.mapper.FundJdzfMapper;
import cn.coolwang.crawler.fund.mapper.FundMapper;
import cn.coolwang.crawler.fund.service.FundCrawler;
import cn.coolwang.crawler.fund.service.IFundService;
import cn.coolwang.crawler.fund.vo.FundDetailVO;
import cn.coolwang.crawler.fund.vo.FundJdzfVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class FundServiceIml implements IFundService {

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private FundJdzfMapper fundJdzfMapper;

    @Autowired
    private FundCrawler fundCrawler;

    @Override
    public FundEntity queryFundDetail(String fundCode) {
        FundEntity fundEntity = fundMapper.selectById(fundCode);
        //如果基金不存在或者数据已经存在超过7天则从新获取
        if (Objects.isNull(fundEntity) || fundEntity.getUpdateTime().toLocalDateTime().isAfter(LocalDateTime.now().minusDays(7))) {
            FundDetailVO detailVO = fundCrawler.getFundDetail(fundCode);
            if (Objects.nonNull(detailVO)) {
                if (Objects.isNull(fundEntity)) {
                    fundEntity = detailVO.covertBean(FundEntity.class);
                    fundMapper.insert(fundEntity);
                } else {
                    fundEntity = detailVO.covertBean(FundEntity.class);
                    fundMapper.updateById(fundEntity);
                }

            }
        }
        //获取收益率
        List<FundJdzfVO> fundJdzfVOS = fundCrawler.getFundJdSyl(fundCode);
        String fundName = fundEntity.getFundName();
        fundJdzfVOS.parallelStream().forEach(vo -> {
            FundJdzfEntity entity = vo.covertBean(FundJdzfEntity.class);
            entity.setFundName(fundName);
            fundJdzfMapper.insert(entity);
        });
        return fundEntity;
    }
}