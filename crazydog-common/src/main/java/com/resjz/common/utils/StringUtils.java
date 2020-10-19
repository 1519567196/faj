package com.resjz.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
  private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	  
	  public static String byteArrayToHexString(byte[] b) {   
	    StringBuffer resultSb = new StringBuffer();   
	    for (int i = 0; i < b.length; i++) {   
	      resultSb.append(byteToHexString(b[i]));   
	    }   
	    return resultSb.toString();   
	  }   
	  
	  private static String byteToHexString(byte b) {   
	    int n = b;   
	    if (n < 0)   
	      n = 256 + n;   
	    int d1 = n / 16;   
	    int d2 = n % 16;   
	    return hexDigits[d1] + hexDigits[d2];   
	  }   
	  
	  public static String MD5Encode(String origin) {   
	    String resultString = null;   
	  
	    try {   
	      resultString = new String(origin);   
	      MessageDigest md = MessageDigest.getInstance("MD5");   
	      resultString = byteArrayToHexString(md.digest(resultString.getBytes()));  
	      //resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
	    } catch (Exception ex) {   
	  
	    }   
	    return resultString;   
	  }   
	  
	  
	  
	  
	  /**
	   * 是否是邮箱
	   * @param str
	   * @return
	   */
	  public static boolean isemail(String str){

	       String regex = "[a-zA-Z0-9_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;

	        return match( regex ,str );


	  }
	  /**
	   * 整数字验证 
	   * @param str
	   * @return
	   */
	  public static boolean isDigital(String str){

	      try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} 
		  
	  }
	  /**
	   * 小数字验证 
	   * @param str
	   * @return
	   */
	  public static boolean isDouble(String str){

	      try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} 
		  
	  }
	  /**
	   * 判断账号性质，字母，数字，下划线组合，不能以数字开头。
	   * @param accout
	   * @return
	   */
	  public static boolean isAccount(String accout){
		  if(!StringUtils.notNullAndEmpty(accout))return false;
		  String start=accout.substring(0,1);
		  return !isDigital(start)&&accout.matches("[A-Za-z0-9_]+");
	  }
	  /**
	   * 数字和字母组合
	   * @Title: 
	   * @return boolean
	   * @author zrh
	   * @date 2017年8月16日
	   */
	  public static boolean isDigitalLetter(String accout){
		  if(accout==null)return false;
		  return accout.matches("[A-Za-z0-9_]+");
	  }
	  /**
	   * 
	   * @param dd 要处理的double数据
	   * @param wit 小数点后几位
	   * @return
	   */
	  public static double getDoubles(double dd,int wit){
		  String wstr="1";
		  for(int i=0;i<wit;i++){
			  wstr+="0";
		  }
		  wstr+=".0";
		  double ww=Double.valueOf(wstr);
		  long   l1   =   Math.round(dd*ww);   //四舍五入   
	      double   ret   =   l1/ww;               //注意：使用   100.0   而不是   100   
	      return ret;
	  }
	  /**
	   * 手机验证
	   * @return
	   */
	  public static boolean isTel(String str){
		  String regex = "^((13[0-10])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		  int start=str.indexOf("1");
		  if(start==0 && str.length()==11){
			  try {
				  Long l=Long.parseLong(str);
				  return true;
			} catch (Exception e) {
				return false;
			}
		  }

	        return false;

	  }
	  /**
	   * 是否为手机或者固话
	   * @Title: 
	   * @param str
	   * @return
	   * @return boolean
	   * @author zrh
	   * @date 2017年5月26日
	   */
	  public static boolean isPhone(String str){
//		  String regExp ="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}|[0]{1}[0-9]{2,3}-[0-9]{7,8}$";
		  String regExp ="^[0]{1}[0-9]{2,3}-[0-9]{7,8}$";
		  Pattern p = Pattern.compile(regExp);
		  Matcher m = p.matcher(str);
		  return m.find();

	  }
	  
	  /**
	   * 判断字符串 非null 非空
	   * s!=null && s.trim().length()>0
	   * @param s
	   * @return
	   */
	  public static boolean notNullAndEmpty(String s){
		  if(s!=null && s.trim().length()>0){
			  return true;
		  }
		  return false;
	  }
	  

	  /**
	   * 通过 表id 生产16位随机数 aababab...aaaaa a代表随机数 b代表id
	   * @return
	   */
	  public static String getSigleDigte(String tblid,int perch){
			String rel10=null;
			int idlen=tblid.length();
			int relstrlen=2*idlen+1;//生产后的字符数量
			if(relstrlen>perch){
				perch=relstrlen;
			}
			int rcount=idlen+1;
			String random=getRandCode(1, rcount);
			char[] idchar=tblid.toCharArray();
			char[] rchar=random.toCharArray();
			StringBuffer sb=new StringBuffer();
			sb.append(rchar[0]);
			for(int i=1;i<rcount;i++){
				sb.append(rchar[i]);
				sb.append(idchar[i-1]);
			}
			rel10=sb.toString()+getRandCode(1,perch-rcount-idlen);
			return rel10;
		  }
	  /**
	   * 返回一定长度的字符串 tblid 不够 前面补0
	   * @param tblid 要操作的字符串
	   * @param perch 返回字符串的长度
	   * @return
	   */
	  public static String getLenString(String tblid,int perch){
		  String fm="%0"+perch+"d";
	      String str2 =String.format(fm, Integer.valueOf(tblid));  
			return str2;
	  }
	  
	  /**
	   * 获取随机 验证码 type=1 数字 2字母 3数字和字母组合
	   * len 返回 位数。len<=0 则返回 4位
	   * @return
	   */
	  public static String getRandCode(int type,int length){
		  String soucrstr="2345678qwertyupkhfdsazxcvbnm";
		  int lens=length;
		  if(type == 1){
		    soucrstr="1234567890";
		  }else if(type == 2){
		    soucrstr="qwertyupkhfdsazxcvbnm";
		  }
	        Random r = new Random(); 
			StringBuffer sb = new StringBuffer(); 
			char[] ch = soucrstr.toCharArray();    
	        int index, len = ch.length;  
	        for (int i = 0; i < lens; i++) {   
	            index = r.nextInt(len);     
	            sb.append(ch[index]);   
	        }
			
	        return sb.toString();
	  }
	  
	    private static boolean match( String regex ,String str ){

	        Pattern pattern = Pattern.compile(regex);

	        Matcher  matcher = pattern.matcher( str );

	        return matcher.matches();

	    }

		  /**
		   * 去掉开头的分割符号
		   * @param sourcestr
		   * @param split
		   * @return
		   */
		  public static String removeFirstSplit(String sourcestr,String split){
			  if(sourcestr!=null){
				  int frist=sourcestr.indexOf(split);
				  int len=sourcestr.length();
				  if(frist==0){
					  sourcestr=sourcestr.substring(1, len);
				  }
			  }
			  return sourcestr;
		  } 
		  /**
		   * 去掉 结尾的分隔符号
		   */
		  public static String removeLastSplit(String sourcestr,String split){
			  if(notNullAndEmpty(sourcestr)){
				  int last=sourcestr.lastIndexOf(split);
				  int len=sourcestr.length();
				  if(last==len-1){
					  sourcestr=sourcestr.substring(0, len-1);
				  }
			  }
			  return sourcestr;
		  }

	    private static final String CHARSET_NAME = "UTF-8";

	    /**
	     * 转换为字节数组
	     * @param str
	     * @return
	     */
	    public static byte[] getBytes(String str){
	    	if (str != null){
	    		try {
					return str.getBytes(CHARSET_NAME);
				} catch (UnsupportedEncodingException e) {
					return null;
				}
	    	}else{
	    		return null;
	    	}
	    }
}
