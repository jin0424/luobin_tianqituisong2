package com.luobin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    /**
     * 昨天天气
     */
     private Yesterday yesterday;

    /**
     * 地区
     */
    private String city;

    /**
     * 今天+未来4天天气
     */
    private List<Forecast> forecast;

    /**
     * 感冒情况
     */
    private String ganmao;

    /**
     * 当前温度
     */
    private String wendu;


}
