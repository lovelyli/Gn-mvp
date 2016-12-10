package com.project.gnet.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author GaoNan
 * 
 */
public class DateTimeUtil {

	/**
	 * 距离时间戳的差值
	 */
	public static String getDifference(long time){
		String str ;
		if (time<1000*60){
			str = intToTime(time,"ss秒前");
		}else {
			str = intToTime(time,"mm分钟前");
		}
		if (str!=null&&str.startsWith("0")){
			str = str.substring(1);
		}
		return str;
	}

	public static String intToTime(long time, String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(time);
	}

	/**
	 * 日期统一格式
	 */
	private final static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static String getTime(long time , String mat){
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(mat);
		return sdf.format(date);
	}
	/**
	 * 获取下一秒的时间
	 * 
	 * @param currentDate
	 * @return
	 */
	public static String getDateAddOneSecond(String currentDate) {

		String nextSecondDate = "";

		if (currentDate != null && !currentDate.equals("")) {

			try {
				Date date = format.parse(currentDate); // 将当前时间格式化
				// System.out.println("front:" + format.format(date)); //
				// 显示输入的日期
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.SECOND, 1); // 当前时间加1秒
				date = cal.getTime();
				// System.out.println("after:" + format.format(date));
				nextSecondDate = format.format(date); // 加一秒后的时间
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nextSecondDate;
	}

	/**
	 * 获取剩余时间 几天几时几分几秒
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getRemainTime(String startTime, String endTime) {

		String remainTime = "0"; // 剩余时间

		long dayMsec = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long hourMsec = 1000 * 60 * 60;// 一小时的毫秒数
		long minuteMsec = 1000 * 60;// 一分钟的毫秒数
		long secondMsec = 1000;// 一秒钟的毫秒数
		long diffMsec; // 毫秒差

		if (startTime != null && !startTime.equals("") && endTime != null
				&& !endTime.equals("")) {
			try {
				// 获得两个时间的毫秒时间差异
				diffMsec = format.parse(endTime).getTime()
						- format.parse(startTime).getTime();
				if(diffMsec > 0){
					/*判断结束时间是否大于开始时间*/
					long diffDay = diffMsec / dayMsec;// 计算差多少天
					long diffHour = diffMsec % dayMsec / hourMsec;// 计算差多少小时
					long diffMin = diffMsec % dayMsec % hourMsec / minuteMsec;// 计算差多少分钟
					long diffSec = diffMsec % dayMsec % dayMsec % minuteMsec
							/ secondMsec;// 计算差多少秒//输出结果
					remainTime = diffDay + "天" + diffHour + "时" + diffMin + "分"
							+ diffSec + "秒";
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return remainTime;
	}
	/**
	 * 获取剩余时间 几天几时几分几秒
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getRemainHours(String startTime, String endTime) {

		String remainTime = "0"; // 剩余时间
//		long dayMsec = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long hourMsec = 1000 * 60 * 60;// 一小时的毫秒数
		long minuteMsec = 1000 * 60;// 一分钟的毫秒数
		long secondMsec = 1000;// 一秒钟的毫秒数
		long diffMsec; // 毫秒差

		if (startTime != null && !startTime.equals("") && endTime != null
				&& !endTime.equals("")) {
			try {
				// 获得两个时间的毫秒时间差异
				diffMsec = format.parse(endTime).getTime()
						- format.parse(startTime).getTime();
				if(diffMsec > 0){
					/*判断结束时间是否大于开始时间*/
//					long diffDay = diffMsec / dayMsec;// 计算差多少天
					long diffHour = diffMsec  / hourMsec;// 计算差多少小时
					long diffMin = diffMsec  % hourMsec / minuteMsec;// 计算差多少分钟
//					long diffSec = diffMsec  % minuteMsec
//							/ secondMsec;// 计算差多少秒//输出结果
					remainTime =  diffHour + ":" + diffMin;
//							+ "分"
//							+ diffSec + "秒";
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return remainTime;
	}
	/**
	 * 格式化日期格式
	 * 
	 * @param dateTimeString
	 * @return
	 */
	public static String formatDateType(String dateTimeString) {

		String formatAfterDateTimeString = "";
		// System.out.println(dateTimeString);

		if (dateTimeString != null && !dateTimeString.equals("")) {
			/* 判断字符串是否有值 */
			formatAfterDateTimeString = dateTimeString;

			if (formatAfterDateTimeString.contains("/")) {
				/* 判断日期中是否包含'/' */
				formatAfterDateTimeString = formatAfterDateTimeString.replace(
						"/", "-");
			}

			if ((formatAfterDateTimeString.lastIndexOf("-") - formatAfterDateTimeString
					.indexOf("-")) == 2) {
				/* 判断月份格式是否是MM格式 */
				String frontSubString = formatAfterDateTimeString.substring(0,
						formatAfterDateTimeString.indexOf("-") + 1);
				String afterSubString = "0" + formatAfterDateTimeString.substring(
						formatAfterDateTimeString.indexOf("-") + 1,
						formatAfterDateTimeString.length());
				
				formatAfterDateTimeString = frontSubString + afterSubString; //拼接字符串
			}
		}
		return formatAfterDateTimeString;
	}

	public static String getCurrentTimeText(){
		long time= System.currentTimeMillis();
		Date date=new Date(time);
		return format.format(date);
	}
	public static String getCurrentTimeText(String formatstr){
		long time= System.currentTimeMillis();
		Date date=new Date(time);
		SimpleDateFormat sf=new SimpleDateFormat(formatstr);
		return sf.format(date);
	}
//	public static String getNumAfterTimeText(String formatstr,int num){
//		long time=System.currentTimeMillis()+num*86400000;
//		Date date=new Date(time);
//		SimpleDateFormat sf=new SimpleDateFormat(formatstr);
//		return sf.format(date);
//	}
	public static String stringToDate(String datestr, String formatstr){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date!=null){
			return new SimpleDateFormat(formatstr).format(date);
		}else {
			return getCurrentTimeText(formatstr);
		}
	}


	public static long getLongTime(char dateType) {
		Calendar c = Calendar.getInstance(); // 当时的日期和时间
		int hour; // 需要更改的小时
		int day; // 需要更改的天数
		switch (dateType) {
			case '0': // 1小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 1;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '1': // 2小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 2;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '2': // 3小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 3;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '3': // 6小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 6;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '4': // 12小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 12;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '5': // 一天前
				day = c.get(Calendar.DAY_OF_MONTH) - 1;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '6': // 一星期前
				day = c.get(Calendar.DAY_OF_MONTH) - 7;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '7': // 一个月前
				day = c.get(Calendar.DAY_OF_MONTH) - 30;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '8':
				day = c.get(Calendar.DAY_OF_MONTH) + 90;
				c.set(Calendar.DAY_OF_MONTH, day);
				break;
		}
		return c.getTimeInMillis();
	}


	/**
	 * 获取阶段日期
	 * @param  dateType
	 * @author Yangtse
	 */
	//使用方法 char datetype = '7';
	public static StringBuilder getPeriodDate(char dateType) {
		Calendar c = Calendar.getInstance(); // 当时的日期和时间
		int hour; // 需要更改的小时
		int day; // 需要更改的天数
		switch (dateType) {
			case '0': // 1小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 1;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '1': // 2小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 2;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '2': // 3小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 3;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '3': // 6小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 6;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '4': // 12小时前
				hour = c.get(Calendar.HOUR_OF_DAY) - 12;
				c.set(Calendar.HOUR_OF_DAY, hour);
				// System.out.println(df.format(c.getTime()));
				break;
			case '5': // 一天前
				day = c.get(Calendar.DAY_OF_MONTH) - 1;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '6': // 一星期前
				day = c.get(Calendar.DAY_OF_MONTH) - 7;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '7': // 一个月前
				day = c.get(Calendar.DAY_OF_MONTH) - 30;
				c.set(Calendar.DAY_OF_MONTH, day);
				// System.out.println(df.format(c.getTime()));
				break;
			case '8'://三个月后
				day = c.get(Calendar.DAY_OF_MONTH) + 90;
				c.set(Calendar.DAY_OF_MONTH, day);
				break;
		}
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		StringBuilder strForwardDate = new StringBuilder().append(mYear).append(
				(mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append(
				(mDay < 10) ? "0" + mDay : mDay);
		System.out.println("strDate------->"+strForwardDate+"-"+c.getTimeInMillis());
		return strForwardDate;
	}




}
