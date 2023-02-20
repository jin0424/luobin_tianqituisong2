package com.luobin.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class WindscUtils {

    /**
     * 风力名称与颜色转换
     * @param fengli
     * @return
     */
    public static Map windsc(String fengli) {

        Integer fengliI = Integer.valueOf(Pattern.compile("级").matcher(fengli).replaceAll(""));

        String fengliName = null; String fengliColour = null;
        if (fengliI == 0){
            fengliName = "无风"; fengliColour = "#000000";
        } else if (fengliI < 3) {
            fengliName = "轻风"; fengliColour = "#000000";
        } else if (fengliI == 3) {
            fengliName = "微风"; fengliColour = "#000000";
        } else if (fengliI == 4) {
            fengliName = "和风"; fengliColour = "#E6E600";
        } else if (fengliI == 5) {
            fengliName = "清风"; fengliColour = "#E67300";
        } else if (fengliI == 6) {
            fengliName = "强风"; fengliColour = "#FF3333";
        } else if (fengliI == 7) {
            fengliName = "劲风+"; fengliColour = "#B32400";
        } else if (fengliI > 7) {
            fengliName = "大风+"; fengliColour = "#800026";
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("windscName", fengliName);
        map.put("windscColour", fengliColour);

        return map;
    }
}
