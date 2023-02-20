package com.luobin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.luobin.pojo.Forecast;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WeatherUtils {

    /**
     * 地区
     */
    private static String city;
    /**
     * 彩虹屁id
     */
    private static String chpKet;

    @Value("${com.city}")
    public void setSecret(String city) {WeatherUtils.city= city;}
    @Value("${com.chpKet}")
    public void setChpKet(String chpKet) {WeatherUtils.chpKet= chpKet;}

    /**
     * 天气情况获取
     * @return
     * @throws JsonProcessingException
     */
    public static Forecast getWeather() throws JsonProcessingException {
        RestTemplate restTemplate = new AppConfig().restTemplate();
        String res = restTemplate.getForObject( "https://apis.tianapi.com/tianqi/index?city="+city+"&key="+chpKet+"&type=1", String.class);
        JSONObject json = JSONObject.parseObject(res);
        String code = json.get("code").toString();
        if (!"200".equals(code)){
            log.error("未获取到天气情况");
            return new Forecast();
        }

        String date = json.getJSONObject("result").get("date").toString(); // 日期
        String week = json.getJSONObject("result").get("week").toString(); // 星期
        String province = json.getJSONObject("result").get("area").toString(); // 所属省份
        String weather = json.getJSONObject("result").get("weather").toString(); // 实时天气（七天为早晚变化）
        String real = json.getJSONObject("result").get("real").toString(); // 实时气温（七天仅为参考）
        String lowest = json.getJSONObject("result").get("lowest").toString(); // 最低温（夜间温度）
        String highest = json.getJSONObject("result").get("highest").toString(); // 最高温（日间温度）
        String wind = json.getJSONObject("result").get("wind").toString(); // 风向（方位）
        String windspeed = json.getJSONObject("result").get("windspeed").toString(); // 风速，km/h
        String windsc = json.getJSONObject("result").get("windsc").toString(); // 风力
        String pcpn = json.getJSONObject("result").get("pcpn").toString(); // 降雨量（毫米）
        String uvIndex = json.getJSONObject("result").get("uv_index").toString(); // 紫外线强度指数
        String aqi = json.getJSONObject("result").get("aqi").toString(); // 空气质量指数（七天无此字段）
        String quality = json.getJSONObject("result").get("quality").toString(); // 空气质量提示（七天无此字段）
        String vis = json.getJSONObject("result").get("vis").toString(); // 能见度（公里）
        String humidity = json.getJSONObject("result").get("humidity").toString(); // 相对湿度（百分比）
        String alarmlist = json.getJSONObject("result").get("alarmlist").toString(); //
        String tips = json.getJSONObject("result").get("tips").toString(); // 生活指数提示

        Forecast forecast = new Forecast();

        if (StringUtils.isNoneBlank(alarmlist)){
            List<Forecast> jsonList = JSONArray.parseArray(alarmlist, Forecast.class);
            forecast.setAlarmlist(jsonList);
        }

        forecast.setDate(date);
        forecast.setWeek(week);
        forecast.setProvince(province);
        forecast.setWeather(weather);
        forecast.setReal(real);
        forecast.setLowest(lowest);
        forecast.setHighest(highest);
        forecast.setWind(wind);
        forecast.setWindspeed(windspeed);
        forecast.setWindsc(windsc);

        Map windscNC = WindscUtils.windsc(windsc);
        forecast.setWindscName(windscNC.get("windscName").toString());
        forecast.setWindscColour(windscNC.get("windscColour").toString());
        forecast.setPcpn(pcpn);
        forecast.setUvIndex(uvIndex);
        forecast.setAqi(aqi);
        forecast.setQuality(quality);
        forecast.setVis(vis);
        forecast.setHumidity(humidity);
        forecast.setTips(tips);

        return forecast;
    }


}

@Configuration
class AppConfig {

    /**
     * 编码格式转换
     * 使用@Bean 注解表明myBean需要交给Spring进行管理
     * 未指定bean 的名称，默认采用的是 "方法名" + "首字母小写"的配置方式
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        List<HttpMessageConverter<?>>httpMessageConverters = restTemplate.getMessageConverters();
        httpMessageConverters.stream().forEach(httpMessageConverter -> {
            if(httpMessageConverter instanceof StringHttpMessageConverter){
                StringHttpMessageConverter  messageConverter = (StringHttpMessageConverter) httpMessageConverter;
                messageConverter.setDefaultCharset(Charset.forName("UTF-8")); // 编码格式转换
            }
        });
        return restTemplate;
    }
}
