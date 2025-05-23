
package com.zt.eweb.framework.mybatis.core.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 提供常用的日期操作的工具类
 *
 * @author
 * @version 2.0
 * @date 2019/01/01
 */

@SuppressWarnings({"unused", "SpellCheckingInspection", "AlibabaConstantFieldShouldBeUpperCase",
    "AlibabaUndefineMagicConstant", "AlibabaClassNamingShouldBeCamel"})
public class D extends DateUtils {

  /**
   * 日期时间格式
   */
  public static final String FORMAT_DATE_y2M = "yyMM";
  public static final String FORMAT_DATE_y2Md = "yyMMdd";
  public static final String FORMAT_DATE_y4 = "yyyy";
  public static final String FORMAT_DATE_y4Md = "yyyyMMdd";
  public static final String FORMAT_TIMESTAMP = "yyMMddHHmmss";
  public static final String FORMAT_TIMESTAMP_Y4MDHM = "yyMMddHHmm";
  public static final String FORMAT_TIME_HHmm = "HH:mm";
  public static final String FORMAT_TIME_HHmmss = "HH:mm:ss";
  public static final String FORMAT_DATE_Y4MD = "yyyy-MM-dd";
  public static final String FORMAT_DATETIME_Y4MDHM = "yyyy-MM-dd HH:mm";
  public static final String FORMAT_DATETIME_Y4MDHMS = "yyyy-MM-dd HH:mm:ss";
  public static final String FORMAT_DATE_SLASH_Y4MD = "yyyy/MM/dd";
  public static final String FORMAT_DATETIME_SLASH_Y4MDHM = "yyyy/MM/dd HH:mm";
  public static final String FORMAT_DATETIME_SLASH_Y4MDHMS = "yyyy/MM/dd HH:mm:ss";
  public static final String FORMAT_DATE_SLASH_MDY4 = "MM/dd/yyyy";
  public static final String FORMAT_DATETIME_SLASH_MDY4HM = "MM/dd/yyyy HH:mm";
  public static final String FORMAT_DATETIME_SLASH_MDY4HMS = "MM/dd/yyyy HH:mm:ss";
  public static final String FORMAT_DATETIME_Y4MD_T_HMS = "yyyy-MM-ddTHH:mm:ss";
  /**
   * LocalDate 日期 格式化定义
   */
  public static final DateTimeFormatter FORMATTER_DATE_Y4MD = DateTimeFormatter.ofPattern(
      FORMAT_DATE_Y4MD);
  /**
   * LocalDateTime 日期时间 格式化定义（不含秒）
   */
  public static final DateTimeFormatter FORMATTER_DATETIME_Y4MDHM = DateTimeFormatter.ofPattern(
      FORMAT_DATETIME_Y4MDHM);
  /**
   * LocalDateTime 日期时间 格式化定义
   */
  public static final DateTimeFormatter FORMATTER_DATETIME_Y4MDHMS = DateTimeFormatter.ofPattern(
      FORMAT_DATETIME_Y4MDHMS);
  /**
   * Time 时间 格式化定义
   */
  public static final DateTimeFormatter FORMATTER_TIME_HM = DateTimeFormatter.ofPattern(
      FORMAT_TIME_HHmm);
  /**
   * 星期（中文）
   */
  public static final String[] WEEK = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四",
      "星期五", "星期六"};
  /**
   * 星期（英文）
   */
  public static final String[] WEEK_EN = new String[]{"Sun", "Mon", "Tue", "Wed", "Thur", "Fri",
      "Sat"};
  /**
   * 月份-中文
   */
  public static final String[] MONTH_CN = new String[]{"一月", "二月", "三月", "四月", "五月",
      "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
  /**
   * 月份-英文
   */
  public static final String[] MONTH_EN = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
      "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
  /**
   * 时间类型-毫秒数定义
   */
  public static final long MS_1SECOND = 1000;
  public static final long MS_1MINUTE = 60 * MS_1SECOND;
  public static final long MS_1HOUR = 60 * MS_1MINUTE;
  public static final long MS_1DAY = 24 * MS_1HOUR;
  private static final Logger log = LoggerFactory.getLogger(DateUtils.class);
  /**
   * 格式化日期部分字符串
   */
  private static String[] FORMAT_SEARCH_LIST = {"年", "月", "日", "/",
      "\\."}, FORMAT_REPLACEMENT_LIST = {"-", "-", "", "-", "-"};

  /**
   * 当前的日期时间
   *
   * @return format指定格式的日期时间
   */
  public static String now(String format) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(cal.getTime());
  }

  /**
   * 当前日期时间串
   *
   * @return yyMMddhhmmss
   */
  public static String toTimestamp(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_TIMESTAMP);
    return sdf.format(date.getTime());
  }

  /**
   * 获取月份
   */
  @Deprecated
  public static String getMonth() {
    return now(FORMAT_DATE_y2M);
  }

  /**
   * 获取今天的日期
   *
   * @return yyyyMMdd
   */
  public static String today() {
    return now(FORMAT_DATE_y4Md);
  }

  /**
   * 转换字符串为日期date
   */
  public static Date convert2FormatDate(String datetime, String fmt) {
    if (StringUtils.isBlank(datetime)) {
      return null;
    }
    SimpleDateFormat format = new SimpleDateFormat(fmt);
    try {
      return format.parse(datetime);
    } catch (ParseException e) {
      log.warn("日期格式转换异常");
    }
    return null;
  }

  /**
   * 转换date为日期Y4MD格式化字符串
   */
  public static String convert2DateString(Date date) {
    return convert2FormatString(date, FORMAT_DATE_Y4MD);
  }

  /**
   * 转换date为日期时间Y4MDHMS格式化字符串
   */
  public static String convert2DateTimeString(Date date) {
    return convert2FormatString(date, FORMAT_DATETIME_SLASH_Y4MDHMS);
  }

  /**
   * 转换date为格式化字符串
   */
  public static String convert2FormatString(Date date, String fmt) {
    if (date == null) {
      return null;
    } else {
      SimpleDateFormat format = new SimpleDateFormat(fmt);
      return format.format(date);
    }
  }

  /**
   * 获取格式化的日期
   *
   * @param date       基准日期
   * @param daysOffset 偏移量
   * @return yyyy-MM-dd
   */
  public static String getDate(Date date, int... daysOffset) {
    return getDate(date, FORMAT_DATE_Y4MD, daysOffset);
  }

  /**
   * 获取日期的下一天
   *
   * @param date 基准日期
   * @return yyyy-MM-dd
   */
  public static Date nextDay(Date date) {
    if (date == null) {
      return null;
    }
    return addDays(date, 1);
  }

  /**
   * 获取格式化的日期时间
   */
  public static String getDateTime(Date date, int... daysOffset) {
    return getDate(date, FORMAT_DATETIME_Y4MDHM, daysOffset);
  }

  /**
   * 获取格式化的日期时间
   *
   * @param date       日期
   * @param format     日期格式
   * @param daysOffset 偏移天数，在日期上add，要么不传，要么只有一个值
   */
  public static String getDate(Date date, String format, int... daysOffset) {
    if (date == null) {
      date = new Date();
    }
    if (daysOffset != null && daysOffset.length > 0) {
      date = addDays(date, daysOffset[0]);
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  /**
   * 是否是工作时间段，用于后台程序等
   *
   * @return 是否是工作时间段
   */
  public static boolean isWorkingTime() {
    Calendar cal = Calendar.getInstance();
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    return (hour >= 8 && hour < 20);
  }

  /**
   * 获取上午/下午
   *
   * @return 上午/下午
   */
  public static String getAmPm() {
    Calendar c = Calendar.getInstance();
    int hours = c.get(Calendar.HOUR_OF_DAY);
    if (hours <= 9) {
      return "早上";
    } else if (hours <= 12) {
      return "上午";
    } else if (hours == 13) {
      return "中午";
    } else if (hours <= 18) {
      return "下午";
    } else {
      return "晚上";
    }
  }

  /**
   * 得到当前的年月yyMM，用于生成文件夹名称
   *
   * @return 年月
   */
  public static String getYearMonth() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_y2M);
    return sdf.format(cal.getTime());
  }

  /**
   * 得到当前的年月日yyMMdd，用于生成文件夹
   *
   * @return 年月日
   */
  public static String getYearMonthDay() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_y2Md);
    return sdf.format(cal.getTime());
  }

  /**
   * 得到当前的月，用于生成文件夹
   *
   * @return 月
   */
  public static int getDay() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 获取日期对应的星期
   */
  @Deprecated
  public static String getWeek(Date date) {
    return getCnWeek(date);
  }

  /**
   * 获取当前日期对应的星期（中文）
   */
  public static String getCnWeek() {
    int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    return WEEK[index];
  }

  /**
   * 获取日期对应的星期（中文）
   */
  public static String getCnWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return WEEK[cal.get(Calendar.DAY_OF_WEEK) - 1];
  }

  /**
   * 获取当前日期对应的星期（英文）
   */
  public static String getEnWeek() {
    int index = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    return WEEK_EN[index];
  }

  /**
   * 获取日期对应的星期（英文）
   */
  public static String getEnWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return WEEK_EN[cal.get(Calendar.DAY_OF_WEEK) - 1];
  }

  /**
   * 获取当前日期对应的月份（中文）
   */
  public static String getCnMonth() {
    return MONTH_CN[Calendar.getInstance().get(Calendar.MONTH)];
  }

  /**
   * 获取指定日期对应的月份（中文）
   *
   * @param date 日期
   */
  public static String getCnMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return MONTH_CN[cal.get(Calendar.MONTH)];
  }

  /**
   * 获取当前日期对应的月份（英文）
   */
  public static String getEnMonth() {
    return MONTH_EN[Calendar.getInstance().get(Calendar.MONTH)];
  }

  /**
   * 获取指定日期对应的月份（中文）
   *
   * @param date 日期
   */
  public static String getEnMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return MONTH_EN[cal.get(Calendar.MONTH)];
  }

  /**
   * 毫秒数转date
   */
  public static Date timeMillis2Date(Long timeMillis) {
    return new Date(timeMillis);
  }

  /**
   * 字符串时间戳转日期
   */
  public static Date datetimeString2Date(String value) {
    return convert2DateTime(value, FORMAT_DATETIME_Y4MDHMS);
  }

  /**
   * 转换耗时毫秒数为 d,h,m,s显示文本
   *
   * @param duration 时长毫秒数
   */
  public static String formatDurationLabel(Long duration) {
    if (duration == null) {
      return "-";
    }
    long days = duration / MS_1DAY;
    if (days > 0) {
      return days + "d";
    }
    // 必然小于MS_1DAY，不需要取模
    long hours = duration / MS_1HOUR;
    if (hours > 0) {
      return hours + "h";
    }
    long minutes = duration / MS_1MINUTE;
    if (minutes > 0) {
      return minutes + "m";
    }
    long seconds = duration / MS_1SECOND;
    if (seconds > 0) {
      return seconds + "s";
    }
    return "<1s";
  }

  /**
   * 字符串时间戳转日期
   */
  public static Date convert2Date(String date) {
    if (date.contains("/")) {
      return convert2FormatDate(date, FORMAT_DATE_SLASH_Y4MD);
    } else {
      return convert2FormatDate(date, FORMAT_DATE_Y4MD);
    }
  }

  /**
   * 获取两个时间范围内的所有日期
   *
   * @param startTime 包含
   * @param endTime   不包含
   */
  public static List<String> rangeDateList(Date startTime, Date endTime) throws ParseException {
    if (startTime == null || endTime == null || startTime.after(endTime)) {
      return Collections.emptyList();
    }
    //日期工具类准备
    DateFormat format = new SimpleDateFormat(D.FORMAT_DATE_Y4MD);
    String dBegin = format.format(startTime);

    //设置开始时间
    Calendar calBegin = Calendar.getInstance();
    calBegin.setTime(format.parse(dBegin));

    // 两个时间相差的天数
    int days = (int) ((endTime.getTime() - startTime.getTime()) / MS_1DAY);

    //装返回的日期集合容器
    List<String> rangeDateList = new ArrayList<>(days);
    // 每次循环给calBegin日期加一天
    for (int i = 0; i < days; i++) {
      rangeDateList.add(format.format(calBegin.getTime()));
      // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
      calBegin.add(Calendar.DAY_OF_MONTH, 1);
    }
    return rangeDateList;
  }

  /**
   * 字符串时间戳转日期
   */
  public static Date convert2DateTime(String dateTime, String... dateFormat) {
    String f = FORMAT_DATETIME_Y4MDHM;
    if (dateFormat != null && dateFormat.length > 0) {
      f = dateFormat[0];
    }
    return convert2FormatDate(dateTime, f);
  }

  /**
   * 模糊转换日期
   */
  public static Date fuzzyConvert(String dateString) {
    if (V.isEmpty(dateString)) {
      return null;
    }
    dateString = formatDateTimeString(dateString);
    if (!S.contains(dateString, " ")) {
      return convert2FormatDate(dateString, FORMAT_DATE_Y4MD);
    }
    return convert2FormatDate(dateString, FORMAT_DATETIME_Y4MDHMS);
  }

  /**
   * 模糊转换日期
   */
  public static LocalDate convert2LocalDate(String dateString) {
    if (V.isEmpty(dateString)) {
      return null;
    }
    dateString = formatDatePartString(dateString);
    return LocalDate.parse(dateString, FORMATTER_DATE_Y4MD);
  }

  /**
   * 模糊转换日期时间
   */
  public static LocalDateTime convert2LocalDateTime(String dateTimeString) {
    if (V.isEmpty(dateTimeString)) {
      return null;
    }
    dateTimeString = formatDateTimeString(dateTimeString);
    if (dateTimeString.length() <= D.FORMAT_DATE_Y4MD.length()) {
      dateTimeString += " 00:00:00";
    }
    return LocalDateTime.parse(dateTimeString, D.FORMATTER_DATETIME_Y4MDHMS);
  }

  /**
   * @param dateTimeString
   * @return
   * @see #formatDateTimeString(String)
   */
  @Deprecated
  public static String formatDateString(String dateTimeString) {
    return formatDateTimeString(dateTimeString);
  }

  /**
   * 格式化日期字符串
   */
  public static String formatDateTimeString(String dateString) {
    if (V.isEmpty(dateString)) {
      return null;
    }
    String[] parts = (dateString.contains("T") && !dateString.contains(" ")) ? dateString.split("T")
        : dateString.split(" ");
    String datePart = formatDatePartString(parts[0]);
    if (parts.length > 1) {
      String timePart = formatTimePartString(parts[1]);
      return datePart + " " + timePart;
    }
    return datePart;
  }

  private static String formatDatePartString(String datePartStr) {
    if (V.isEmpty(datePartStr)) {
      return null;
    }
    if (S.contains(datePartStr, " ")) {
      datePartStr = S.substringBefore(datePartStr, " ");
    }
    if (S.contains(datePartStr, "-")) {
      String[] ymd = datePartStr.split("-");
      if (ymd.length == 3 && ymd[0].length() == 4) {
        // 标准格式
        return datePartStr;
      }
    } else {
      // 格式化
      datePartStr = S.replaceEach(datePartStr, FORMAT_SEARCH_LIST, FORMAT_REPLACEMENT_LIST);
    }
    String[] ymd = datePartStr.split("-");
    if (ymd.length >= 3) {
      if (ymd[2].length() == 4) { //MM/dd/yyyy
        String yyyy = ymd[2], month = ymd[0], day = ymd[1];
        ymd[0] = yyyy;
        ymd[1] = month;
        ymd[2] = day;
      }
      // 补齐位数
      if (ymd[0].length() == 2) {
        ymd[0] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2) + ymd[0];
      }
      if (ymd[1].length() == 1) {
        ymd[1] = "0" + ymd[1];
      }
      if (ymd[2].length() == 1) {
        ymd[2] = "0" + ymd[2];
      }
    }
    return S.join(ymd, "-");
  }

  /**
   * 格式化时间部分字符串
   */
  private static String formatTimePartString(String timePartStr) {
    if (V.isEmpty(timePartStr)) {
      return null;
    }
    // 18:20:30:103
    String[] hms = timePartStr.split(":");
    if (hms.length == 3) {
      return timePartStr;
    }
    String[] hmsArray = new String[3];
    if (hms[0].length() == 1) {
      hms[0] = "0" + hms[0];
    }
    hmsArray[0] = hms[0];
    if (hms.length >= 2) {
      if (hms[1].length() == 1) {
        hms[1] = "0" + hms[1];
      }
      hmsArray[1] = hms[1];
    } else {
      hmsArray[1] = "00";
    }
    if (hms.length >= 3) {
      if (hms[2].length() == 1) {
        hms[2] = "0" + hms[2];
      }
      hmsArray[2] = hms[2];
    } else {
      hmsArray[2] = "00";
    }
    return S.join(hmsArray, ":");
  }

}
