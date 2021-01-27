package cn.coolwang.crawler.fund.entity;

import cn.coolwang.crawler.pojo.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 基金信息(Fund)表实体类
 *
 * @author 邓军
 * @since 2021-01-25 11:49:35
 */
@Setter
@Getter
@TableName("t_fund")
@SuppressWarnings("serial")
public class FundEntity extends BaseModel {

    /**
     * 基金代码
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String fundCode;

    /**
     * 基金名称
     */
    @TableField("fund_name")
    private String fundName;

    /**
     * 基金类型
     */
    @TableField("fund_type")
    private String fundType;

    /**
     * 天天基金-原始费率
     */
    @TableField("original_rate")
    private Double originalRate;

    /**
     * 天天基金-购买费率
     */
    @TableField("purchase_rate")
    private Double purchaseRate;

    /**
     * 最小申购金额
     */
    @TableField("min_sub_amount")
    private Integer minSubAmount;

    /**
     * 现任基金经理代码
     */
    @TableField("manager_code")
    private String managerCode;

    /**
     * 现任基金经理
     */
    @TableField("manager_name")
    private String managerName;

    /**
     * 阶段收益：今年以来
     */
    @TableField("syl_this_year")
    private Double sylThisYear;

    /**
     * 阶段收益：近一周
     */
    @TableField("syl_1z")
    private Double syl1z;

    /**
     * 阶段收益：近一月
     */
    @TableField("syl_1y")
    private Double syl1y;

    /**
     * 阶段收益：近三月
     */
    @TableField("syl_3y")
    private Double syl3y;

    /**
     * 阶段收益：近六月
     */
    @TableField("syl_6y")
    private Double syl6y;

    /**
     * 阶段收益：近一年
     */
    @TableField("syl_1n")
    private Double syl1n;

    /**
     * 阶段收益：近2年
     */
    @TableField("syl_2n")
    private Double syl2n;

    /**
     * 阶段收益：近3年
     */
    @TableField("syl_3n")
    private Double syl3n;

    /**
     * 阶段收益：近5年
     */
    @TableField("syl_5n")
    private Double syl5n;

    /**
     * 阶段收益：成立以来
     */
    @TableField("syl_build")
    private Double sylBuild;

    /**
     * 特色数据：近一年夏普比率
     */
    @TableField("sharp_1n")
    private Double sharp1n;

    /**
     * 特色数据：近一年波动率
     */
    @TableField("stddev_1n")
    private Double stddev1n;

    /**
     * 特色数据：近一年最大回撤
     */
    @TableField("maxretra_1n")
    private Double maxretra1n;

    /**
     * 特色数据：近一月定投人数
     */
    @TableField("dtcount_1y")
    private Integer dtcount1y;

    /**
     * 特色数据：近一月访问量
     */
    @TableField("pv_1y")
    private Integer pv1y;

    /**
     * 特色数据：用户平均持有天数
     */
    @TableField("avghold")
    private Double avghold;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Timestamp updateTime;

}