package com.myspring.pro27;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;//A Locale object represents a specific geographical, political,or cultural region.

import javax.servlet.http.HttpServletResponse;
//SLF4J(Simple Logging Facade for Java)는 단순한 퍼사드 패턴을 수단으로 하는 자바 로깅 API를 제공한다.
//퍼사드는 "건물의 정면"을 의미
//퍼사드는 클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 간략화된 인터페이스를 제공하는 객체이다.
import org.slf4j.Logger;//
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletResponse response) {
		
		logger.info("Welcome home! The client locale is {}.", locale);//INFO : com.myspring.pro27.HomeController - Welcome home! The client locale is 이부분이 로케일임->ko_KR.
		
		
		response.setContentType("text/html;charset=utf-8");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);//2023년 3월 14일 오전 11시 5분 56초 KST
		System.out.println(formattedDate);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
