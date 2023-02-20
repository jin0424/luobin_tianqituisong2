package com.luobin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 今天+未来7天天气
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
     * 星期
     */
    private String week;

    /**
     * 所属省份
     */
    private String province;

    /**
     * 实时天气（七天为早晚变化）
     */
    private String weather;

    /**
     * 实时气温（七天仅为参考）
     */
    private String real;

    /**
     *最低温（夜间温度）
     */
    private String lowest;

    /**
     *最高温（日间温度）
     */
    private String highest;

    /**
     *风向（方位）
     */
    private String wind;

    /**
     *风速，km/h
     */
    private String windspeed;

    /**
     *风力
     */
    private String windsc;
    private String windscName;
    private String windscColour;

    /**
     *降雨量（毫米）
     */
    private String pcpn;

    /**
     *紫外线强度指数
     */
    private String uvIndex;

    /**
     *空气质量指数（七天无此字段）
     */
    private String aqi;

    /**
     *空气质量提示（七天无此字段）
     */
    private String quality;

    /**
     *能见度（公里）
     */
    private String vis;

    /**
     *相对湿度（百分比）
     */
    private String humidity;

    /**
     *生活指数提示
     */
    private String tips;

    /**
     *天气预警列表（七天无此字段）
     */
    private List<Forecast> alarmlist;

}
