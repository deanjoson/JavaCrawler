package cn.coolwang.crawler.fund.entity;

import cn.coolwang.crawler.pojo.BaseModel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 基金信息(Fund)表实体类
 *
 * @author 邓军
 * @since 2021-01-15 09:59:50
 */
@Setter
@Getter
@TableName("t_fund")
@SuppressWarnings("serial")
public class FundEntity extends BaseModel {

    /**
     * 基金代码
     */
    @TableId(type = IdType.INPUT)
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
     * 今年以来
     */
    @TableField("syl_this_year")
    private Double sylThisYear;

    /**
     * 近一周
     */
    @TableField("syl_1z")
    private Double syl1z;

    /**
     * 近一月
     */
    @TableField("syl_1y")
    private Double syl1y;

    /**
     * 近三月
     */
    @TableField("syl_3y")
    private Double syl3y;

    /**
     * 近六月
     */
    @TableField("syl_6y")
    private Double syl6y;

    /**
     * 近一年
     */
    @TableField("syl_1n")
    private Double syl1n;

    /**
     * 近2年
     */
    @TableField("syl_2n")
    private Double syl2n;

    /**
     * 近3年
     */
    @TableField("syl_3n")
    private Double syl3n;

    /**
     * 近5年
     */
    @TableField("syl_5n")
    private Double syl5n;

    /**
     * 成立以来
     */
    @TableField("syl_build")
    private Double sylBuild;

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