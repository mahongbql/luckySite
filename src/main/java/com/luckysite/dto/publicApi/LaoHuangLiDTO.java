package com.luckysite.dto.publicApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author mahongbin
 * @date 2019/8/28 16:05
 * @Description
 */
@Data
public class LaoHuangLiDTO {

    @ApiModelProperty(value = "阳历(2014-09-11)")
    private String yangli;

    @ApiModelProperty(value = "阴历（甲午(马)年八月十八）")
    private String yinli;

    @ApiModelProperty(value = " 五行(井泉水 建执位)")
    private String wuxing;

    @ApiModelProperty(value = "冲煞（冲兔(己卯)煞东）")
    private String chongsha;

    @ApiModelProperty(value = "彭祖百忌（乙不栽植千株不长 酉不宴客醉坐颠狂）")
    private String baiji;

    @ApiModelProperty(value = "吉神宜趋(官日 六仪 益後 月德合 除神 玉堂 鸣犬)")
    private String jishen;

    @ApiModelProperty(value = "宜（祭祀 出行 扫舍 馀事勿取）")
    private String yi;

    @ApiModelProperty(value = "凶神宜忌(月建 小时 土府 月刑 厌对 招摇 五离)")
    private String xiongshen;

    @ApiModelProperty(value = "忌（诸事不宜）")
    private String ji;
}
