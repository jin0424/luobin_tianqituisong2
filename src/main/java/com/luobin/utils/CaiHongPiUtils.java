package com.luobin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 彩虹屁获取
 */
@Slf4j
@Component
public class CaiHongPiUtils {

    /**
     * 彩虹屁id
     */
    private static String chpKet;

    @Value("${com.chpKet}")
    public void setChpKet(String chpKet) {CaiHongPiUtils.chpKet= chpKet;}

    /**
     * 彩虹屁接口
     * @return
     */
    public static String getCaiHongPi() {
        String httpUrl = "http://api.tianapi.com/caihongpi/index?key=" + chpKet;
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray newslist = jsonObject.getJSONArray("newslist");
        String content = newslist.getJSONObject(0).getString("content");
        return content;
    }

}
