package com.luobin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 今天天气
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Yesterday {

    /**
     * 日期
     */
    private String date;

    /**
     * 最高温
     */
    private String high;

    /**
     * 风向
     */
    private String fx;

    /**
     * 最低温度
     */
    private String low;

    /**
     * 风量级别
     */
    private String fl;

    /**
     * 天气情况
     */
    private String type;
}
