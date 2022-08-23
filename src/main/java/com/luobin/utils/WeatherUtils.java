package com.luobin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luobin.pojo.Forecast;
import com.luobin.pojo.Weather;
import com.luobin.pojo.Yesterday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WeatherUtils {

    private static String city;

    @Value("${com.city}")
    public void setSecret(String city) {WeatherUtils.city= city;}

    /**
     * 天气情况获取
     * @return
     * @throws JsonProcessingException
     */
    public static Weather getWeather() throws JsonProcessingException {
        Map<String,String> map = new HashMap<>();
        map.put("district_id", city);
        RestTemplate restTemplate = new AppConfig().restTemplate();
        String res = restTemplate.getForEntity( "http://wthrcdn.etouch.cn/weather_mini?city={district_id}", String.class, map).getBody();
        JSONObject json = JSONObject.parseObject(res);
        if (!"OK".equals(json.get("desc"))){
            log.error("未获取到天气情况");
            return new Weather();
        }
        String yesterdayS = json.getJSONObject("data").get("yesterday").toString(); // 昨天天气
        String city = json.getJSONObject("data").get("city").toString(); // 获取地址
        String forecastS = json.getJSONObject("data").get("forecast").toString(); // 今天+未来4天天气
        String ganmao = json.getJSONObject("data").get("ganmao").toString(); // 感冒
        String wendu = json.getJSONObject("data").get("wendu").toString(); // 当前温度

        ObjectMapper mapper = new ObjectMapper();
        Yesterday yesterdayM = mapper.readValue(yesterdayS, Yesterday.class);

        List<Forecast> jsonList = JSONArray.parseArray(forecastS, Forecast.class);

        Weather weather = new Weather();
        weather.setYesterday(yesterdayM);
        weather.setForecast(jsonList);
        weather.setCity(city);
        weather.setGanmao(ganmao);
        weather.setWendu(wendu);

        return weather;
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
