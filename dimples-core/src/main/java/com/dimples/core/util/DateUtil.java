package com.dimples.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateException;
import cn.hutool.core.util.StrUtil;

/**
 * 日期工具类
 *
 * @author zhongyj <1126834403@qq.com><br/>
 * @date 2019/11/8
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取两个日期的中间所有日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param result    日期列表
     */
    public static void range(String startDate, String endDate, List<String> result) {
        if (StrUtil.isEmpty(startDate)) {
            throw new DateException("startDate is not null");
        }
        if (StrUtil.isEmpty(endDate)) {
            endDate = cn.hutool.core.date.DateUtil.today();
        }
        Date start = cn.hutool.core.date.DateUtil.parseDate(startDate);
        Date end = cn.hutool.core.date.DateUtil.parseDate(endDate);
        if (start.after(end)) {
            Date tmp = start;
            start = end;
            end = tmp;
        }
        result.add(cn.hutool.core.date.DateUtil.formatDate(start));
        if (!cn.hutool.core.date.DateUtil.isSameDay(start, end)) {
            range(cn.hutool.core.date.DateUtil.formatDate(cn.hutool.core.date.DateUtil.offsetDay(start, 1)), null, result);
        }
    }

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return getTime();
    }

    /**
     * 获取YYYY格式
     *
     * @return String
     */
    public static String getYear() {
        return formatDate(new Date(), YYYY);
    }

    /**
     * 获取YYYY格式
     *
     * @return String
     */
    public static String getYear(Date date) {
        return formatDate(date, YYYY);
    }

    /**
     * 获取YYYY-MM格式
     *
     * @return String
     */
    public static String getYearMonth() {
        return formatDate(new Date(), YYYY_MM);
    }

    /**
     * 获取YYYY-MM格式
     *
     * @return String
     */
    public static String getYearMonth(Date date) {
        return formatDate(date, YYYY_MM);
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return String
     */
    public static String getDay() {
        return formatDate(new Date(), YYYY_MM_DD);
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return String
     */
    public static String getDay(Date date) {
        return formatDate(date, YYYY_MM_DD);
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return String
     */
    public static String getDays() {
        return formatDate(new Date(), YYYYMMDD);
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return String
     */
    public static String getDays(Date date) {
        return formatDate(date, YYYYMMDD);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return String
     */
    public static String getTime() {
        return formatDate(new Date(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss.SSS格式
     *
     * @return String
     */
    public static String getMsTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 获取YYYYMMDDHHmmss格式
     *
     * @return String
     */
    public static String getAllTime() {
        return formatDate(new Date(), YYYYMMDDHHMMSS);
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return String
     */
    public static String getTime(Date date) {
        return formatDate(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, YYYY_MM_DD);
        }
        return formatDate;
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     *
     * @param s String
     * @param e String
     * @return boolean
     */
    public static boolean compareDate(String s, String e) {
        if (parseDate(s) == null || parseDate(e) == null) {
            return false;
        }
        return parseDate(s).getTime() >= parseDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return Date
     */
    public static Date parseDate(String date) {
        return parse(date, YYYY_MM_DD);
    }

    /**
     * 格式化日期
     *
     * @return Date
     */
    public static Date parseTime(String date) {
        return parse(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化日期
     *
     * @return Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return String
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把日期转换为Timestamp
     *
     * @param date Date
     * @return Timestamp
     */
    public static Timestamp format(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * 校验日期是否合法
     *
     * @return boolean
     */
    public static boolean isValidDate(String s) {
        return parse(s, YYYY_MM_DD_HH_MM_SS) != null;
    }

    /**
     * 校验日期是否合法
     *
     * @return boolean
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat(YYYY_MM_DD);
        try {
            return (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
                    / 365);
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * 时间相减得到天数
     *
     * @param beginDateStr String
     * @param endDateStr   String
     * @return long
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (endDate != null) {
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days String
     * @return String
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar calendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        calendar.add(Calendar.DATE, daysInt);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

    /**
     * 得到n天之后是周几
     *
     * @param days String
     * @return String
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        return sdf.format(date);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

}
