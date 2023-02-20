package com.luobin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.luobin.test.LunarCalendarUtil.lunarToSolar;

/**
 * 计算恋爱日期 和 生日倒计时
 */
@Slf4j
@Component
public class JiNianRiUtils {

    /**
     * 恋爱日期
     */
    private static String loveTime;
    /**
     * 对方生日
     */
    private static String birthday;
    /**
     * 是否是阴历
     */
    private static boolean solarCalendar;

    @Value("${com.loveTime}")
    public void setLoveTime(String loveTime) {JiNianRiUtils.loveTime = loveTime;}
    @Value("${com.birthday}")
    public void setBirthday(String birthday) {JiNianRiUtils.birthday = birthday;}
    @Value("${com.solarCalendar}")
    public void setBirthday(boolean solarCalendar) {JiNianRiUtils.solarCalendar = solarCalendar;}

    /**
     * 计算相恋天数
     * @return
     */
    public static int getLianAi(){
        return calculationLianAi(loveTime);
    }

    /**
     * 计算生日倒计时
     * @return
     */
    public static int getBirthday_Jo(){
        try {
            return calculationBirthday(birthday, solarCalendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算生日
     * @param clidate： 对方生日
     * @return
     * @throws ParseException
     */
    public static int calculationBirthday(String clidate, boolean type) throws ParseException {
        // true为阴历（农历）需要准换为阳历（公历）
        if (type){
            // 生日转list
            List<String> birthdayDate = Arrays.asList(clidate.split("-"));
            // 获取今年时间
            int year = Calendar.getInstance().get(Calendar.YEAR);
            // 将今年时间写入参数
            birthdayDate.set(0, String.valueOf(year));
            // 开始阴历转阳历
            String solarCalendarDate = Arrays.toString(lunarToSolar(Integer.parseInt(birthdayDate.get(0)), Integer.parseInt(birthdayDate.get(1)), Integer.parseInt(birthdayDate.get(2)), false));
            // 去除收尾字符
            List<String> date = Arrays.asList((solarCalendarDate.substring(0, solarCalendarDate.length()-1).substring(1)).split(","));
            // 格式转换
            clidate = date.get(0) + "-" + date.get(1).replace(" ", "") + "-" + date.get(2).replace(" ", "");
        }

        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果
        return days;
    }

    /**
     * 计算相恋天数
     * @param date
     * @return
     */
    public static int calculationLianAi(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

}
