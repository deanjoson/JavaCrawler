package cn.coolwang.crawler.fund.service.impl;

import cn.coolwang.crawler.fund.entity.FundEntity;
import cn.coolwang.crawler.fund.entity.FundJdzfEntity;
import cn.coolwang.crawler.fund.mapper.FundJdzfMapper;
import cn.coolwang.crawler.fund.mapper.FundMapper;
import cn.coolwang.crawler.fund.service.FundCrawler;
import cn.coolwang.crawler.fund.service.IFundService;
import cn.coolwang.crawler.fund.vo.FundBaseVO;
import cn.coolwang.crawler.fund.vo.FundDetailVO;
import cn.coolwang.crawler.fund.vo.FundJdzfVO;
import cn.coolwang.crawler.util.CollectionUtils;
import cn.coolwang.crawler.util.NumberUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void saveAllFundBase() {
        LocalDateTime now = LocalDateTime.now();
        List<FundEntity> exists = fundMapper.selectList(null);
        List<String> existsFundCode = exists.parallelStream().map(FundEntity::getFundCode).collect(Collectors.toList());

        List<FundBaseVO> fundBaseVOS = fundCrawler.getAllFund();
        List<FundEntity> fundEntities = fundBaseVOS.parallelStream()
                .filter(vo -> !existsFundCode.contains(vo.getFundCode()))
                .map(vo -> {
                    FundEntity fundEntity = new FundEntity();
                    fundEntity.setFundCode(vo.getFundCode());
                    fundEntity.setFundName(vo.getFundName());
                    fundEntity.setFundType(vo.getFundType());
                    fundEntity.setCreateTime(Timestamp.valueOf(now));
                    fundEntity.setUpdateTime(Timestamp.valueOf(now));
                    return fundEntity;
                }).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(fundEntities)) {
            fundMapper.insertAll(fundEntities);
        }
    }

    @Override
    public void updateFundDetail(String fundCode) {
        FundEntity fundEntity = fundMapper.selectById(fundCode);
        boolean exists = true;
        if (Objects.isNull(fundEntity)) {
            exists = false;
            fundEntity = new FundEntity();
            log.warn("基金信息不存在，请先获取基金类型");
        }

        //获取基金基本信息
        FundDetailVO detailVO = fundCrawler.getFundDetail(fundCode);
        if (Objects.isNull(detailVO)) {
            throw new RuntimeException("获取基金详细信息失败");
        }
        fundEntity.setFundCode(detailVO.getFundCode());
        fundEntity.setFundName(detailVO.getFundName());
        fundEntity.setManagerCode(detailVO.getManagerCode());
        fundEntity.setManagerName(detailVO.getManagerName());
        fundEntity.setMinSubAmount(detailVO.getMinSubAmount());
        fundEntity.setOriginalRate(detailVO.getOriginalRate());
        fundEntity.setPurchaseRate(detailVO.getPurchaseRate());

        //获取基金阶段收益
        FundJdzfVO jdzfVO = updateFundJdsy(fundCode, detailVO.getFundName());

        fundEntity.setSylThisYear(NumberUtils.parseDouble(jdzfVO.getSylThisYear(), "%"));
        fundEntity.setSyl1z(NumberUtils.parseDouble(jdzfVO.getSyl1z(), "%"));
        fundEntity.setSyl1y(NumberUtils.parseDouble(jdzfVO.getSyl1y(), "%"));
        fundEntity.setSyl3y(NumberUtils.parseDouble(jdzfVO.getSyl3y(), "%"));
        fundEntity.setSyl6y(NumberUtils.parseDouble(jdzfVO.getSyl6y(), "%"));
        fundEntity.setSyl1n(NumberUtils.parseDouble(jdzfVO.getSyl1n(), "%"));
        fundEntity.setSyl2n(NumberUtils.parseDouble(jdzfVO.getSyl2n(), "%"));
        fundEntity.setSyl3n(NumberUtils.parseDouble(jdzfVO.getSyl3n(), "%"));
        fundEntity.setSyl5n(NumberUtils.parseDouble(jdzfVO.getSyl5n(), "%"));
        fundEntity.setSylBuild(NumberUtils.parseDouble(jdzfVO.getSylBuild(), "%"));
        if (exists) {
            fundMapper.updateById(fundEntity);
        } else {
            fundMapper.insert(fundEntity);
        }
    }

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

    /**
     * 更新基金阶段收益返回涨幅信息
     *
     * @param fundCode
     */
    private FundJdzfVO updateFundJdsy(String fundCode, String fundName) {

        //先删除已有
        LambdaQueryWrapper<FundJdzfEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FundJdzfEntity::getFundCode, fundCode);
        fundJdzfMapper.delete(wrapper);

        List<FundJdzfVO> fundJdzfVOS = fundCrawler.getFundJdSyl(fundCode);
        fundJdzfVOS.parallelStream().forEach(vo -> {
            FundJdzfEntity entity = vo.covertBean(FundJdzfEntity.class);
            entity.setFundName(fundName);
            fundJdzfMapper.insert(entity);
        });

        return fundJdzfVOS.parallelStream().filter(vo -> vo.getType() == 1).findFirst().get();
    }
}
