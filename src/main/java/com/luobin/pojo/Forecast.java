package com.luobin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 今天+未来4天天气
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    /**
     * 日期
     */
    private String date;

    /**
     * 最高温
     */
    private String high;

    /**
     * 风量级别
     */
    private String fengli;

    /**
     * 最低温度
     */
    private String low;

    /**
     * 风向
     */
    private String fengxiang;

    /**
     * 天气情况
     */
    private String type;
}
