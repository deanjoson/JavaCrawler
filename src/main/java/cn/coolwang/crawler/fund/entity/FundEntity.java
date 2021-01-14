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
 * @since 2021-01-14 16:15:35
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
     * 原始费率
     */
    @TableField("original_rate")
    private Double originalRate;

    /**
     * 购买费率
     */
    @TableField("purchase_rate")
    private Double purchaseRate;

    /**
     * 最小申购金额
     */
    @TableField("min_sub_amount")
    private Integer minSubAmount;

    /**
     * 近一月收益率
     */
    @TableField("syl_1y")
    private Double syl1y;

    /**
     * 近三月收益率
     */
    @TableField("syl_3y")
    private Double syl3y;

    /**
     * 近6月收益率
     */
    @TableField("syl_6y")
    private Double syl6y;

    /**
     * 近一年收益率
     */
    @TableField("syl_1n")
    private Double syl1n;

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