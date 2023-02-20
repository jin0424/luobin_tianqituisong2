package com.luobin.test;

import com.luobin.utils.LunarCalendarUtils;

import java.util.Arrays;
import java.util.List;

public class DateInterconversion {

    /**
     * 阴历阳历时间互转
     * @param args
     */
    public static void main(String[] args) {
        String dateS = "2023-07-13";
        List<String> strings = Arrays.asList(dateS.split("-"));

        // 将公历日期转换为农历日期，且标识是否是闰月
        String solarToLunar = Arrays.toString(LunarCalendarUtils.solarToLunar(Integer.parseInt(strings.get(0)), Integer.parseInt(strings.get(1)), Integer.parseInt(strings.get(2))));
        System.out.println("注意：非闰月为0");
        System.out.println("阳历："+dateS+" / 阴历："+solarToLunar);

        // 将农历日期转换为公历日期
        String lunarToSolar = Arrays.toString(LunarCalendarUtils.lunarToSolar(Integer.parseInt(strings.get(0)), Integer.parseInt(strings.get(1)), Integer.parseInt(strings.get(2)), false));
        System.out.println("阴历："+dateS+" / 阳历："+lunarToSolar);

        // 去除收尾字符
        List<String> strings2 = Arrays.asList((lunarToSolar.substring(0, lunarToSolar.length()-1).substring(1)).split(","));
        // 转换格式
        String lunarToSolar_ = strings2.get(0) + "-" + strings2.get(1).replace(" ", "") + "-" + strings2.get(2).replace(" ", "");
        System.out.println("阴历："+dateS+" / 阳历："+lunarToSolar_);
    }
}
