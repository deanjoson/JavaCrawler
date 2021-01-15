package cn.coolwang.crawler.fund.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 季度收益类型
 *
 * @author 邓军
 * @version 1.0
 * @date
 */
@AllArgsConstructor
@Getter
public enum JdsyType {

    ZF(1, "涨幅"),
    TLPJ(2, "同类平均"),
    HS300(3, "沪深300"),
    TLPM(4, "同类排名"),
    FWPM(5, "四分位排名"),
    ;

    private Integer type;

    private String typeDesc;

    /**
     * 根据类型秒速获取季度收益
     *
     * @param typeDesc
     * @return
     */
    public static JdsyType getByDesc(String typeDesc) {
        for (JdsyType jdsyType : JdsyType.values()) {
            if (Objects.equals(jdsyType.typeDesc, typeDesc)) {
                return jdsyType;
            }
        }
        return null;
    }

    /**
     * 根据类型秒速获取季度收益
     *
     * @param type
     * @return
     */
    public static JdsyType getByType(Integer type) {
        for (JdsyType jdsyType : JdsyType.values()) {
            if (Objects.equals(jdsyType.type, type)) {
                return jdsyType;
            }
        }
        return null;
    }
}
