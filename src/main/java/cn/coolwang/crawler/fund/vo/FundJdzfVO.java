package cn.coolwang.crawler.fund.vo;

import cn.coolwang.crawler.pojo.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FundJdzfVO extends BaseModel {
    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金名称
     */
    private String fundName;

    /**
     * 涨幅类型
     */
    private Integer type;

    /**
     * 涨幅类型描述
     */
    private String typeDesc;

    /**
     * 今年以来
     */
    private String sylThisYear;

    /**
     * 近一周
     */
    private String syl1z;

    /**
     * 近一月
     */
    private String syl1y;

    /**
     * 近三月
     */
    private String syl3y;

    /**
     * 近六月
     */
    private String syl6y;

    /**
     * 近一年
     */
    private String syl1n;

    /**
     * 近2年
     */
    private String syl2n;

    /**
     * 近3年
     */
    private String syl3n;

    /**
     * 近5年
     */
    private String syl5n;

    /**
     * 成立以来
     */
    private String sylBuild;
}
