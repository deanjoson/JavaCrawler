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
 * 基金阶段涨幅信息(FundJdzf)表实体类
 *
 * @author 邓军
 * @since 2021-01-14 18:28:14
 */
@Setter
@Getter
@TableName("t_fund_jdzf")
@SuppressWarnings("serial")
public class FundJdzfEntity extends BaseModel {

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
     * 涨幅类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 涨幅类型描述
     */
    @TableField("type_desc")
    private String typeDesc;

    /**
     * 今年以来
     */
    @TableField("syl_this_year")
    private String sylThisYear;

    /**
     * 近一周
     */
    @TableField("syl_1z")
    private String syl1z;

    /**
     * 近一月
     */
    @TableField("syl_1y")
    private String syl1y;

    /**
     * 近三月
     */
    @TableField("syl_3y")
    private String syl3y;

    /**
     * 近六月
     */
    @TableField("syl_6y")
    private String syl6y;

    /**
     * 近一年
     */
    @TableField("syl_1n")
    private String syl1n;

    /**
     * 近2年
     */
    @TableField("syl_2n")
    private String syl2n;

    /**
     * 近3年
     */
    @TableField("syl_3n")
    private String syl3n;

    /**
     * 近5年
     */
    @TableField("syl_5n")
    private String syl5n;

    /**
     * 成立以来
     */
    @TableField("syl_build")
    private String sylBuild;

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