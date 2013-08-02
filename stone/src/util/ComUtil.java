package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 공통 함수 클래스
 * @author stoneis.pe.kr
 *
 */
public class ComUtil {
	
	/**
	 * 현재날짜를 얻는 함수 
	 * EX) 2013-01-01 01:01:01
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date current = new Date();
		String date = formater.format(current);
		Calendar cal = new GregorianCalendar();
		int mHour = cal.get(Calendar.HOUR_OF_DAY);
		int mMinute = cal.get(Calendar.MINUTE);
		int mSecond = cal.get(Calendar.SECOND);
		int mMilliSecond = cal.get(Calendar.MILLISECOND);
		String nowDate = date + " " + mHour + ":" + mMinute + ":" + mSecond + "." + mMilliSecond;
		return nowDate;
	}
	
}
