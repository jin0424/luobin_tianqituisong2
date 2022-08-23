package com.luobin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static com.luobin.utils.LunarCalendarUtil.lunarToSolar;

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

    public static int getLianAi(){
        return calculationLianAi(loveTime);
    }
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
    public static int calculationBirthday(String clidate, boolean solarCalendar) throws ParseException {
        if (solarCalendar){
            int[] ints = lunarToSolar(1998, 9, 18, true);
            StringBuilder clidates = new StringBuilder();
            for (int i = 0; i < ints.length; i++) {
                if (i == 1 || i == 2) {
                    String valueOf = String.valueOf(ints[i]);
                    if ( valueOf.length() != 2 ){
                        clidates.append("-").append("0").append(ints[i]); continue;
                    }
                    clidates.append("-").append(ints[i]); continue;
                }
                clidates.append(ints[i]);
            }
            clidate = clidates.toString();
        }
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cToday = Calendar.getInstance();
        Calendar cBirth = Calendar.getInstance();
        cBirth.setTime(myFormatter.parse(clidate));
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }

        if (days == 0) {
            return 0;
        } else {
            return days;
        }
    }

    /**
     * 计算天数
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
