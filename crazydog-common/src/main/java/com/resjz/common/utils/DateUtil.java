package com.resjz.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	
	private DateUtil(){}	
	private static DateUtil dateUtil;
	public final static String timeDf_n="YYYY";
	public static synchronized  DateUtil getInstance() {
		   if (dateUtil == null) 
			   dateUtil = new DateUtil();
			return dateUtil;
		}
	
    /**
     * "yyyy-MM"
     */
	public static String timeDf_ny="yyyy-MM";
	/**
     *yyyy-MM-dd
     */
	public static String timeDf_nyr="yyyy-MM-dd";
	/**
	 * yyyy-MM-dd HH:mm
	 */
	public static String timeDf_nyrsf="yyyy-MM-dd HH:mm";
	/**
	 * yyyy-MM-dd HH
	 */
	public static String timeDf_nyrs="yyyy-MM-dd HH";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String timeDf_nyrsfm="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期字符串-->Date
	 * 如果日期字符串长度大于10 转换时分秒，
	 * 否则 转换年月日
	 * @param sDate
	 * @return
	 */
	public  Date stringToDate(String sDate){
		Date d=null;
		SimpleDateFormat df=null;
		if(sDate!=null){
			if(sDate.length()>10){
				df = new SimpleDateFormat(timeDf_nyrsfm);
			}else{
				df = new SimpleDateFormat(timeDf_nyr);
			}
		}else{
			return d;
		}
		try {
			d=df.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 返回传入日期所在的周的周一的日期
	 * @param timel 10位 日期
	 * @return "yyyy-MM-dd" 周一日期
	 */
	public String getWeekStart(Long timel){
		Calendar cal=dateToCalendar(longToDate(timel));
		
//		cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天，星期日是第一天，星期一是第二天......  
		int cd=0;
		if (dayOfWeek == 1) {  
			cd= -6;  
		} else {  
			cd= 2 - dayOfWeek;  
		}  
		Long ll=addDay(timel, cd);
		
	   return LongtoString(ll, false);
	}
	/**
	 * 返回 传入日期所在的周的周日的日期
	 * @param timel 10位
	 * @return
	 */
	public String getWeekEnd(Long timel){
		String startl=getWeekStart(timel);
		Long slong=stringToLong(startl);
		Long endl=addDay(slong,6);
	   return LongtoString(endl, false);
	}
	
	
	/**
	 * 字符串-->Long 10位 精确到秒
	 * datatype 格式化 日期 类型
	 * @param sDate
	 * @return
	 */
	public Long stringToLong(String sDate,String dataType){
		Long l = null;
		if(sDate!=null && !"".equals(sDate)){
			SimpleDateFormat df=null;
			Date d=null;
			try {
			   if(StringUtils.notNullAndEmpty(dataType)){
				   df = new SimpleDateFormat(dataType);
				   d=df.parse(sDate);
			   }else{
				   d=stringToDate(sDate);
			   }
			   
			   l = d.getTime()/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return l;
	}
	/**
	 * 字符串转换long型，先将字符串转换Date，再将date转换Long
	 *Long
	 *@param sDate
	 *@return
	 *@author zrh
	 *@ 2017年3月28日
	 */
	public Long stringToLong(String sDate){
		Long l = null;
		if(sDate!=null && !"".equals(sDate)){
			Date d = stringToDate(sDate);
			if(d==null){
				return null;
			}
			l = d.getTime()/1000;
		}
		return l;
	}
	/**
	 * 10位 long型
	 * @param ldate
	 * @return
	 */
	public Date longToDate(Long ldate){
		Date d=new Date(ldate*1000);
		return d;
	}
	/**
	 * Date-->字符串
	 * @param d
	 * @param sfm true返回时分秒
	 * @return
	 */
	public  String dateToString(Date d,boolean sfm){
        if(sfm)return dateToStirng(d, 0, 19);
        return dateToStirng(d,0, 10);
	}
	/**
	 * 10位Long--->日期字符串 带有连接符 2013/10/10 12:51:22
	 * @param lDate
	 * @param sfm true 返回带有时分秒
	 * @return
	 */
	public String LongtoString(Long lDate,boolean sfm){
		String s =null;
		Date d = new Date(lDate*1000);
		s=dateToString(d,sfm);
		return s;
	}
	/**
	 * 
	 * @param lDate
	 * @param df 'yyyy-MM-dd HH:mm:ss'
	 * @return
	 */
	public String LongtoString(Long lDate,String df){
		if(lDate==null){
			return "";
		}
		String s =null;
		Date d = new Date(lDate*1000);
		SimpleDateFormat sdf = new SimpleDateFormat(df);
	    s =sdf.format(d);
		return s;
	}
	/**
	 * 10位Long--->日期字符串 无连接符 20131010125122
	 * @param lDate
	 * @param sfm true 返回带有时分秒
	 * @return
	 */
	public String LongtoStringNoSign(Long lDate,boolean sfm){
		 String s =null;
		 Date d = new Date(lDate*1000);
         s=dateToString(d, sfm);
		return s.replace("-", "").replace(":", "").replace(" ", "");
   }
	/**
	 * 当前日期所在月的一号，例如2017-08-01
	 * @param date
	 * @return
	 */
	public String getMouthStart(Date date){
		String s="";
		s=dateToStirng(date, 0, 7);
		return s+"-01";
	}
	/**
	 * 当前日期所在月的一号，Long型

	 * @return
	 */
	public Long getMouthStart(Long time){
		Date date = longToDate(time);
		String s=getMouthStart(date);
		Long rl=stringToLong(s);
		return rl;
	}
	/**
	 * 当前日期所在月的一号，例如2017-08-01

	 * @return
	 */
	public String getMouthStartStr(Long time){
		Date date = longToDate(time);
		String s=getMouthStart(date);
		return s;
	}
	
	/**
	 * 日期 --->Long 10位Long型
	 * @param d
	 * @return
	 */
	public Long dateToLong(Date d){
		if(d!=null){
			return d.getTime()/1000;
		}
		return null;
	}
	/**
	 * 返回 时间差 单位秒
	 */
	public Long getBetweenTime(Date smallDate,Date bigDate) {
		Long rel=0L;
		Long oldlong=this.dateToLong(smallDate);
		Long newlong=this.dateToLong(bigDate);
		
		return getBetweenTime(oldlong, newlong);
	}
	/**
	 * 返回 时间差 单位秒
	 * @param smallDate
	 * @param bigDate
	 * @return
	 */
	public Long getBetweenTime(Long smallDate,Long bigDate){
		Long cha=bigDate-smallDate;
//		Long yc=(cha/1000);
		return cha;
	}
	/**
	 * 增(n>0)减(n<0)月份 n个月
	 * @param date
	 * @param n
	 * @return
	 */
	public Date addMouth(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, n);
		return c.getTime();
	}
	/**
	 * 增(n>0)减(n<0)月份 n个月
	 * @param date
	 * @param n
	 * @return
	 */
	public Long addMouth(Long date, int n) {
		// TODO Auto-generated method stub
		Date d=this.longToDate(date);
		d=addMouth(d, n);
		return dateToLong(d);
	}
	
	/**
	 * 增加 天数 time 10位 精确秒 返回10位
	 * @return
	 */
	 public  Long  addDay(Long time,int n)   {   
		    Date d=longToDate(time);
		    Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.DATE, n);
			return c.getTime().getTime()/1000;
	}
	/**
	 * 增加 小时 n个小时
	 * @param date
	 * @param n
	 * @return
	 */
	public Date addHour(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, n);
		return c.getTime();
	}
	public Long addHour(Long time,int n){
		Date d=longToDate(time);
		d=addHour(d, n);
		return dateToLong(d);
	 }
	/**
	 * 增加 分钟 n分钟
	 * @param date
	 * @param n
	 * @return
	 */
	public Date addMinute(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, n);
		return c.getTime();
	}
	public Long addMinute(Long time,int n){
		Date d=longToDate(time);
		d=addMinute(d, n);
		return dateToLong(d);
	 }
	/**
	 * 增(n>0)减(n<0)秒 n秒
	 * @param date
	 * @param n
	 * @return
	 */
	public Date addSecond(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, n);
		return c.getTime();
	}
	/**
	 * 增(n>0)减(n<0)秒
	 * @param time
	 * @param n
	 * @return
	 */
	public Long addSecond(Long time,int n){
		Date d=longToDate(time);
		d=addSecond(d, n);
		return dateToLong(d);
	 }
	
	/***************************************************************************
	 * 函数名称： getYear()<br>
	 * 函数功能： 取得年份<br>
	 * 返回值： int<br>
	 * 参数说明： 无<br>
	 * 
	 * 最后修改： 无
	 **************************************************************************/
	public int getYear(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.YEAR);
	}

	/***************************************************************************
	 * 函数名称： getMonth()<br>
	 * 函数功能： 取得月份<br>
	 * 返回值： int<br>
	 * 参数说明： 无<br>
	 * 最后修改： 无
	 **************************************************************************/
	public int getMonth(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.MONTH)+1;
	}

	/***************************************************************************
	 * 函数名称： getSunr()<br>
	 * 函数功能： 取得日份<br>
	 * 返回值： int<br>
	 * 参数说明： 无<br>
	 * 最后修改： 无
	 **************************************************************************/
	public int getSun(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.DATE);
	}

	 /*************************************************** 函数名称： getHour()<br>
	 * 函数功能： 取得小时<br>
	 * 返回值： int<br>
	 * 参数说明： 无<br>
	 * 最后修改： 无
	 **************************************************************************/
	public int getHour(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/***************************************************************************
	 * 函数名称： getMinu()<br>
	 * 函数功能： 取得分钟<br>
	 * 返回值： int<br>
	 * 参数说明： 无<br>
	 * 最后修改： 无
	 **************************************************************************/
	public int getMinu(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.MINUTE);
	}

	/***************************************************************************
	 * 函数名称： getSecond()<br>
	 * 函数功能： 取得秒数<br>
	 * 返回值： String<br>
	 * 参数说明： 无<br>
	 * 最后修改： 无
	 **************************************************************************/
	public int getSecond(Date d) {
		Calendar c = dateToCalendar(d);
		return c.get(Calendar.SECOND);
	}
	/**
	 * 日期--》字符串.beginIndex，endIndex：字符串截取 比如：(date,0,10)返回 yyyy-MM-dd
	 * @param d
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public String dateToStirng(Date d,int beginIndex,int endIndex){
		SimpleDateFormat sdf = new SimpleDateFormat(timeDf_nyrsfm);
	    String s =sdf.format(d);
	    if(beginIndex<0)beginIndex=0;
	    if(endIndex>19)endIndex=19;
		return s.substring(beginIndex, endIndex);
	}
	

	/**
	 * 获取 与当前时间差的分钟数
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 获取当前时间 10位 精确到秒
	 * @return
	 */
	public Long getcurrentTime(){
		return System.currentTimeMillis()/1000;
	}

	
	/**
	 * 获取 日期对应的星期,1:星期一,2:星期二...
	 * @Title: 
	 * @param d
	 * @return
	 * @return int
	 * @author zrh
	 * @date 2017年5月15日
	 */
	public int getWeek(Date d) {
		// TODO Auto-generated method stub
       Calendar cal=dateToCalendar(d);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天，星期日是第一天，星期一是第二天......  
		int zday=dayOfWeek-1;
		if(zday==0){
			zday=7;
		}
		return zday;
	}
	
	
	private Calendar dateToCalendar(Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}
	private  SimpleDateFormat format;

	public DateUtil(SimpleDateFormat format) {
		this.format = format;
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	//紧凑型日期格式，也就是纯数字类型yyyyMMdd
	public static final DateUtil COMPAT = new DateUtil(new SimpleDateFormat("yyyyMMdd"));

	//常用日期格式，yyyy-MM-dd
	public static final DateUtil COMMON = new DateUtil(new SimpleDateFormat("yyyy-MM-dd"));
	public static final DateUtil COMMON_FULL = new DateUtil(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

	//使用斜线分隔的，西方多采用，yyyy/MM/dd
	public static final DateUtil SLASH = new DateUtil(new SimpleDateFormat("yyyy/MM/dd"));

	//中文日期格式常用，yyyy年MM月dd日
	public static final DateUtil CHINESE = new DateUtil(new SimpleDateFormat("yyyy年MM月dd日"));
	public static final DateUtil CHINESE_FULL = new DateUtil(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));

	/**
	 * 日期获取字符串
	 */
	public String getDateText(Date date){
		return getFormat().format(date);
	}

	/**
	 * 字符串获取日期
	 * @throws ParseException
	 */
	public Date getTextDate(String text) throws ParseException{
		return getFormat().parse(text);
	}

	/**
	 * 日期获取字符串
	 */
	public static String getDateText(Date date ,String format){
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 字符串获取日期
	 * @throws ParseException
	 */
	public static Date getTextDate(String dateText ,String format) throws ParseException{
		return new SimpleDateFormat(format).parse(dateText);
	}

	/**
	 * 根据日期，返回其星期数，周一为1，周日为7
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK);
		int ret;
		if (w == Calendar.SUNDAY)
			ret = 7;
		else
			ret = w - 1;
		return ret;
	}

	public static String getSeqString() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
		return fm.format(new Date());
	}


}
