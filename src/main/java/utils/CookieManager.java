package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	// 쿠키 생성 메소드
		// 명시한 이름, 값, 유지기간
	public static void makeCookie (HttpServletResponse response, String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie (cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	
	// 쿠키 정보를 읽는 메소드
		// 명시한 쿠키 이름을 찾아 그 값을 반환하는 메소드
	public static String readCookie (HttpServletRequest request, String cName) {
		String cookieValue = "";
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				
				// 매개변수 cName에 해당하는 value를 리턴 
				if (cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		
		return cookieValue;
	}
	
	// 쿠키 삭제 메소드
	public static void deleteCookie (HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
	}
	
}