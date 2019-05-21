package com.example.mmall.util;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static final String DATE_DIVISION = "-";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String TIME_PATTERN_MM = "yyyy-MM-dd HH:mm";

	/**
	 * yyyy-MM-dd HH:00
	 */
	public static final String TIME_PATTERN_YMDH = "yyyy-MM-dd HH:00";

	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

	/**
	 * yyyyMMdd
	 */
	public static final String DATA_PATTERN_YYYYMMDD = "yyyyMMdd";

	/**
	 * yyyyMM
	 */
	public static final String DATA_PATTERN_YYYYMM = "yyyyMM";

	/**
	 * MMDD
	 */
	public static final String DATA_PATTERN_MMDD = "MM-dd";

	/**
	 * HH:mm:ss
	 */
	public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";
	
	/**
	 * HH:mm:ss
	 */
	public static final String TIME_PATTERN_HHMM = "HH:mm";

	public static final int SECOND = 1000;

	public static final int MINUTE = 60 * SECOND;

	public static final int HOUR = 60 * MINUTE;

	public static final long DAY = 24l * HOUR;

	
	/**
	 * Return the current date
	 * 
	 * @return － DATE<br>
	 */
	public static Date now()
	{
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return currDate;
	}

	/**
	 * Return the pattern end date
	 * 
	 * @return － DATE<br>
	 */
	public static Date nowYearEnd()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2099);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DATE, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date currDate = cal.getTime();

		return currDate;
	}

	public static Timestamp nowTimestamp()
	{
		Calendar cal = Calendar.getInstance();
		return new Timestamp(cal.getTimeInMillis());
	}

	/**
	 * Return the current date string
	 * 
	 * @return － 产生的日期字符串<br>
	 */
	public static String nowString()
	{
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return formatDate(currDate);
	}

	/**
	 * Return the current date in the specified format
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String nowString(String pattern)
	{
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return format(currDate, pattern);
	}

	/**
	 * Parse a string and return a date value
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String dateValue)
	{
		return parse(dateValue, DATE_PATTERN_DEFAULT);
	}

	public static void main(String[] args)
	{
		System.out.print(format(addMonths(parseDate("2010-08-31"), 6), "yyyy-MM-dd"));

	}

	/**
	 * Parse a strign and return a datetime value
	 * 
	 * @param dateValue
	 * @return
	 */
	public static Date parseTime(String dateValue)
	{
		return parse(dateValue, TIME_PATTERN_DEFAULT);
	}

	/**
	 * Parse a string and return the date value in the specified format
	 * 
	 * @param strFormat
	 * @param dateValue
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Date parse(String dateValue, String pattern)
	{
		if (StringUtils.isEmpty(dateValue))
			return null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		try
		{
			return dateFormat.parse(dateValue);
		}
		catch (ParseException pe)
		{
			return null;
		}
	}

	/**
	 * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 *            需要转换的日期。
	 * @return 转换后符合给定格式的日期字符串
	 */
	public static String formatDate(Date d)
	{
		return format(d, DATE_PATTERN_DEFAULT);
	}

	/**
	 * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 *            需要转换的日期。
	 * @return 转换后符合给定格式的日期字符串
	 */
	public static String formatTime(Date t)
	{
		return format(t, TIME_PATTERN_DEFAULT);
	}

	/**
	 * 将Date类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 */
	public static String format(Date d, String pattern)
	{
		if (d == null)
			return null;

		SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
		return dateFromat.format(d);
	}

	/**
	 * 取得指定日期N天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, days);

		return cal.getTime();
	}

	/**
	 * 取得指定日期N月后的日期
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addMonths(Date date, int months)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, months);

		return cal.getTime();
	}

	/**
	 * 将日期的年份推后year年,(year 为负时,向前推)
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int year)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.YEAR, year);

		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date getDateBeforTwelveMonth()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MONTH, -12);

		return cal.getTime();

	}

	/**
	 * 传入时间字符串,加一天后返回Date
	 * 
	 * @param date
	 *            时间 格式 YYYY-MM-DD
	 * @return
	 */
	public static Date addDate(String date)
	{
		if (date == null)
			return null;

		Date tmpDate = parse(date, DATE_PATTERN_DEFAULT);

		Calendar cal = Calendar.getInstance();
		cal.setTime(tmpDate);
		cal.add(Calendar.DAY_OF_MONTH, 1);

		return cal.getTime();
	}

	/**
	 * 得到某个日期的星期
	 * 
	 * @param dt
	 * @return Day of the week (0 - sunday)
	 */
	public static int getWeekOfDate(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return w;
	}

	/**
	 * 得到当月第1天日期 yyyy-mm-01
	 * 
	 * @return
	 */
	public static String getCurrentMouDateStr()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return format(cal.getTime(), DATE_PATTERN_DEFAULT);
	}

	/**
	 * 取某一天的开始，即零时零分零秒
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getDateBegin(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		if (dt != null)
			cal.setTime(dt);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取某一天的开始，即零时零分零秒
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getDateEnd(Date dt)
	{
		Calendar cal = Calendar.getInstance();
		if (dt != null)
			cal.setTime(dt);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

	/**
	 * 根据指定日期月份加1且取下个月1号,得到个新日期
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getDateForMonthAddOne(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1);
		Date nextDate = c.getTime();
		return nextDate;

	}

	/**
	 * 根据指定日期月份加n且取下个月,得到个新日期
	 *
	 * @param date
	 * @return date
	 */
	public static Date getDateForMonthAddNew(Date date, int month)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + month, c.get(Calendar.DATE));
		Date nextDate = c.getTime();
		return nextDate;

	}

	/**
	 * 根据指定日期月份减1取上个月,得到个新日期
	 *
	 * @param date
	 * @return date
	 */
	public static Date getDateForMonthRemoveOne(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) - 1, c.get(Calendar.DATE));
		Date nextDate = c.getTime();
		return nextDate;

	}

	/**
	 * 计算两个日期（精确到月）之间相差的年数<br>
	 * date2-date1
	 *
	 * @author xuhj
	 * @created Feb 11, 2009
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int yearsBetween(Date date1, Date date2)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH) + 1;

		cal.setTime(date2);
		int year2 = cal.get(Calendar.YEAR);
		int month2 = cal.get(Calendar.MONTH) + 1;

		int yearBetween = year2 - year1;
		int monthBetween = month2 - month1;

		int betweenMonths = monthBetween + yearBetween * 12;

		return betweenMonths / 12;
	}

	/**
	 * 计算两个日期（精确到日）之间相差的年数<br>
	 * date2-date1
	 *
	 * @author jiangj
	 * @created 2011-07-26
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int yearsBetweenExactDate(Date date1, Date date2)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int year1 = cal.get(Calendar.YEAR);

		cal.setTime(date2);
		int year2 = cal.get(Calendar.YEAR);
		int yearBetween = year2 - year1;
		Date date3 = addYear(date1, yearBetween);
		if (yearBetween > 0)
		{
			if (date3.after(date2))
			{
				return yearBetween - 1;
			}
			else
			{
				return yearBetween;
			}
		}
		else if (yearBetween < 0)
		{
			if (date3.before(date2))
			{
				return yearBetween + 1;
			}
			else
			{
				return yearBetween;
			}
		}
		else
		{
			return yearBetween;
		}
	}

	/**
	 * 根据指定日期下一年
	 *
	 * @param date
	 * @return date
	 */
	public static Date getDateForYearAddOne(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.get(Calendar.YEAR) + 1, c.get(Calendar.MONTH), c.get(Calendar.DATE));
		Date nextYear = c.getTime();
		return nextYear;

	}

	/**
	 * 返回指定日期之间的差距，精确到月
	 * 
	 * @param targetDate
	 * @param baseDate
	 * @return
	 */
	public static String getDiffCalendar(Date targetDate, Date baseDate)
	{
		if (targetDate == null)
		{
			return "";
		}

		Calendar target = Calendar.getInstance();
		target.setTime(targetDate);
		Calendar now = Calendar.getInstance();
		if (baseDate != null)
		{
			now.setTime(baseDate);
		}

		int targetYear = target.get(Calendar.YEAR);
		int nowYear = now.get(Calendar.YEAR);

		int targetMonth = target.get(Calendar.MONTH);
		int nowMonth = now.get(Calendar.MONTH);

		int years = nowYear - targetYear;
		if (years < 1)
			return "";

		int months = nowMonth - targetMonth;
		if (months < 0)
		{
			years = years - 1;
			months = months + 12;
		}
		String age = "";
		if (years > 0)
		{
			age = years + "年";
		}
		if (months > 0)
		{
			age = age + months + "月";
		}
		if (years == 0 || months == 0)
		{
			age = age + "整";
		}
		return age;
	}

	/**
	 * 判断某天时候是周末
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date)
	{
		int day = getWeekOfDate(date);
		if (day <= 0 || day >= 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 比较两个日期是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compare(Date date1, Date date2)
	{
		if (date1 == null && date2 == null)
		{
			return true;
		}
		if (date1 != null && date2 != null)
		{
			return date1.getTime() == date2.getTime();
		}
		return false;
	}


	public static Long getNumBetweenTime(Date begin, Date end)
	{

		if (begin == null || end == null)
		{
			return 0l;
		}

		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(begin);
		Calendar beginDate1 = Calendar.getInstance();
		beginDate1.set(beginDate.get(Calendar.YEAR), beginDate.get(Calendar.MONTH), beginDate.get(Calendar.DATE), 0, 0);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(end);
		Calendar endDate1 = Calendar.getInstance();
		endDate1.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DATE), 0, 0);
		return (endDate1.getTimeInMillis() - beginDate1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
	}

	public static int isAMorPM(Date nowDate)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(nowDate);
		return calendar.get(GregorianCalendar.AM_PM);
	}

}
